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
		this.systemTester = new SystemTester();
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
			insulinPumper.pumpInsulin(dose);
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
		int dose = sugerReading[readingIndex] - safeMax;
		if (dose > maxSingleDose)
			dose = maxSingleDose;
		if (computedDose + dose > maxDailyDose)
			dose = maxDailyDose - computedDose;
		//add here conditon for taking consideration of reservoir and what should it do if  it run out of insulin
		System.out.println("Dose injected-->" + dose);
		return dose;

	}

	public void changeReservoir(Boolean isChanged) {
		if(isChanged)
		reservoir = 100;
	}

	public void saveCurrentSugerMeasure(int sugerReading) {
		this.sugerReading[readingIndex] = sugerReading;

		checkSugerLevel();
		if (readingIndex == 2)
			calcRate();

		readingIndex = (readingIndex + 1) % 3;
	}
}
