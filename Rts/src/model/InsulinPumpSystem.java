package model;

import java.time.LocalTime;

import event.*;
import main.Config;

public class InsulinPumpSystem {

	private Clock clock;
	private Display MyDisplay;
	private SugarMesurment sugarMesurment;
	private SystemTester St;
	private int[] sugerReading;
	private int ReadingIndex;
	private int safeMin;
	private int safeMax;
	private int maxDailyDose;
	private int maxSingleDose;
	private int computedDose;
	private float currentRate;
	private int rateDirection;// -1 decr , 1 inc
	private InsulinPumper insulinPumper;
	public InsulinPumpSystem(HumanBody humanBody) {
		this.clock = new Clock();
		this.MyDisplay = new Display();
		this.St = new SystemTester();
		this.sugarMesurment = new SugarMesurment(humanBody);
		sugerReading = new int[3];
		ReadingIndex = 0;
		safeMin = 70;
		safeMax = 100;
		maxDailyDose = 100;
		maxSingleDose = 60;
		computedDose = 0;
		currentRate = 0;
		rateDirection = 0;
		insulinPumper= new InsulinPumper(humanBody);
	}

	public void Timer(LocalTime time) {
		this.MyDisplay.displayClock(time);
	}

	public void SystemError(String s) {
		this.MyDisplay.displayError(s);
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
		if ((sugerReading[ReadingIndex] > safeMax && rateDirection != -1) || (sugerReading[ReadingIndex] >= safeMin
				&& sugerReading[ReadingIndex] <= safeMax && rateDirection == 1)) {
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
		int dose = sugerReading[ReadingIndex] - safeMax;
		if (dose > maxSingleDose)
			dose = maxSingleDose;
		if (computedDose + dose > maxDailyDose)
			dose = maxDailyDose - computedDose;

		return dose;

	}

	public void addSugerMeasure(int sugerReading) {
		this.sugerReading[ReadingIndex] = sugerReading;

		checkSugerLevel();
		if (ReadingIndex == 2)
			calcRate();

		ReadingIndex = (ReadingIndex + 1) % 3;
	}
}
