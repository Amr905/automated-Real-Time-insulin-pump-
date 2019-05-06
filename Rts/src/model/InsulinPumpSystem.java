package model;

import java.time.LocalTime;

import event.*;
import main.Config;
import view.PumpView;

public class InsulinPumpSystem {

	private PumpView gui;

	private Clock clock;
	private Display myDisplay;
	private SugarMesurment sugarMesurment;
	private SystemTester systemTester;
	private int[] sugarReading;
	private int readingIndex;
	private int safeMin;
	private int safeMax;
	private int maxDailyDose;
	private int maxSingleDose;
	private int computedDose;
	private float currentRate;
	private int rateDirection;// -1 decr , 1 inc
	private InsulinPumper insulinPumper;
	private int reservoir;

	public InsulinPumpSystem(HumanBody humanBody) {

		gui = new PumpView(humanBody,this);
		gui.frame.setVisible(true);

		this.clock = new Clock();
		this.myDisplay = new Display(gui);
		this.sugarMesurment = new SugarMesurment(humanBody);
		sugarReading = new int[3];
		readingIndex = 0;
		safeMin = 70;
		safeMax = 100;
		maxDailyDose = 1000;
		maxSingleDose = 60;
		computedDose = 0;
		currentRate = 0;
		rateDirection = 0;
		insulinPumper = new InsulinPumper(humanBody);
		reservoir = 100;
		this.systemTester = new SystemTester(this);
	}

	public void displayTime(LocalTime time) {
		this.myDisplay.displayClock(time);
	}

	public void displayMsg(String s) {
		this.myDisplay.addMsg(s);
	}

	public void checkSystemSatus(String status) {
		if (!status.equals("OK")) {
			Config.sendEvent(new DisplayMsgEvent(status));
			// Stop();
		}

		else {
			Config.sendEvent(new DisplayMsgEvent("System Working"));
		}

	}

	private void checkSugarLevel() {
		if ((sugarReading[readingIndex] > safeMax && rateDirection != -1) || (sugarReading[readingIndex] >= safeMin
				&& sugarReading[readingIndex] <= safeMax && rateDirection == 1)) {
			int dose = computeDose();
			computedDose += dose;
			if (dose != 0) {
				reservoir-=dose;
				gui.BtnState(true);
				gui.Pumping();
				gui.ResState(reservoir);
				insulinPumper.pumpInsulin(dose);
				System.out.println("Dose injected-->" + dose);
			
			}
		}
		
	}

	private void calcRate() {
		int rate1 = sugarReading[2] - sugarReading[1];
		int rate2 = sugarReading[1] - sugarReading[0];
		if (rate1 > rate2)
			rateDirection = 1;// incr
		else if (rate1 < rate2)
			rateDirection = -1;// dec
		else
			rateDirection = 0;// same

		currentRate = (rate1 + rate2) / 2;
	}

	private int computeDose() {
		int dose = (sugarReading[readingIndex] - safeMax)+10;
		if (dose > maxSingleDose)
			dose = maxSingleDose;
		if (computedDose + dose > maxDailyDose)
			dose = maxDailyDose - computedDose;

		if (reservoir - dose < 0) {
			dose = reservoir;
			Config.sendEvent(new DisplayMsgEvent("Out of insulin, reservoir need to be changed"));
		}

		if(dose<0)
			return 0;
		else
			return dose;

	}

	public void changeReservoir(boolean isChanged) {
		if (isChanged) {
			reservoir = 100;
			System.out.println("resoivir changed"+reservoir);
			Config.sendEvent(new ResetEvent(true));
			Config.sendEvent(new DisplayMsgEvent("System Reseted"));
		}
	}

	public void saveCurrentSugarMeasure(int sugarReading) {
		this.sugarReading[readingIndex] = sugarReading;
		gui.SetSugLvl(sugarReading);
		if (readingIndex == 2)
			calcRate();

		checkSugarLevel();
		readingIndex = (readingIndex + 1) % 3;
	}

	public void displayLastDose(int lastDose) {
		myDisplay.displayLastDose(lastDose);
	}

	public void pumpInsluin(int insulinValue) {
		insulinPumper.pumpInsulin(insulinValue);
	}

	public boolean checkPumperSensor() {
		return insulinPumper.checkSensor();
	
	}

	public boolean checkSugarMesurmentSensor() {
		return sugarMesurment.checkSensor();
	}

	public boolean checkReservoir() {
		if(reservoir>0)return true;
		else return false;
	}
	public void startSytem() {
		displayMsg("System Started");
	}
	public void endSystem() {
		displayMsg("System Ending");

		System.exit(0);
		
	}
	
	public void resetSystem() {
		computedDose = 0;
		currentRate = 0;
		rateDirection = 0;
	}
}
