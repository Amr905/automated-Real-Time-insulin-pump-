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
	private int[] sugerReading;
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

		gui = new PumpView(humanBody);
		gui.frame.setVisible(true);

		this.clock = new Clock();
		this.myDisplay = new Display(gui);
		this.sugarMesurment = new SugarMesurment(humanBody);
		sugerReading = new int[3];
		readingIndex = 0;
		safeMin = 70;
		safeMax = 100;
		maxDailyDose = 100;
		maxSingleDose = 60;
		computedDose = 0;
		currentRate = 0;
		rateDirection = 0;
		insulinPumper = new InsulinPumper(humanBody);
		reservoir = 100;
		this.systemTester = new SystemTester(this);
	}

	public void Timer(LocalTime time) {
		this.myDisplay.displayClock(time);
	}

	public void displayMsg(String s) {
		this.myDisplay.addMsg(s);
	}

	public void checkSystemSatus(String status) {
		if (!status.equals("OK")) {
			Config.sendEvent(new DisplayMsgEvent("ERROR CLOSING"));
			// Stop();
		}

		else {
			Config.sendEvent(new DisplayMsgEvent("System Working"));
		}

	}

	private void checkSugerLevel() {
		if ((sugerReading[readingIndex] > safeMax && rateDirection != -1) || (sugerReading[readingIndex] >= safeMin
				&& sugerReading[readingIndex] <= safeMax && rateDirection == 1)) {
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
		int rate1 = sugerReading[2] - sugerReading[1];
		int rate2 = sugerReading[1] - sugerReading[0];
		if (rate1 > rate2)
			rateDirection = 1;// incr
		else if (rate1 < rate2)
			rateDirection = -1;// dec
		else
			rateDirection = 0;// same

		currentRate = (rate1 + rate2) / 2;
	}

	private int computeDose() {
		int dose = (sugerReading[readingIndex] - safeMax)+10;
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
		if (isChanged)
			reservoir = 100;
		System.out.println("resoivir changed");
	}

	public void saveCurrentSugerMeasure(int sugerReading) {
		this.sugerReading[readingIndex] = sugerReading;

		if (readingIndex == 2)
			calcRate();

		checkSugerLevel();
		readingIndex = (readingIndex + 1) % 3;
	}

	public void displayLastDose(int lastDose) {
		myDisplay.displayLatestDose(lastDose);
	}

	public void pumpInsluin(int insulinValue) {
		insulinPumper.pumpInsulin(insulinValue);
	}

	public boolean checkPumperSensor() {
		return insulinPumper.checkSensor();
	
	}

	public boolean checkSugerMesurmentSensor() {
		return sugarMesurment.checkSensor();
	}

	public boolean checkReservoir() {
		if(reservoir>0)return true;
		else return false;
	}
}
