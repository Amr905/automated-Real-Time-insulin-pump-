package model;

import java.math.RoundingMode;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import event.*;
import main.*;

public class HumanBody  implements Runnable  {

	private int SugarLevel;

	public int getSugarLevel() {
		return SugarLevel;
	}

	public HumanBody() {
		SugarLevel = 75;
	}

	public void raiseSugarLevel() {
		SugarLevel += random(5, 10);
	}

	public void idle() {
		Random r = new Random();
		int rand = (int) Math.round(Math.random());

		if (SugarLevel >= 100) {
			SugarLevel -= random(2, 5);
		} else if (SugarLevel <= 70) {
			SugarLevel += random(2, 5);
		} else if (rand == 0) {
			SugarLevel += random(2, 10);
		} else if (rand == 1) {
			SugarLevel -= random(2, 10);
		}
	}

	public void addInsulin(int insulinValue) {
		while (SugarLevel > SugarLevel + insulinValue) {
			try {
				Thread.sleep(3000);
				SugarLevel -= random(2, 7);
			} catch (InterruptedException ex) {
				Logger.getLogger(HumanBody.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	public void addSuger(int sugarValue) {
		while (SugarLevel < SugarLevel + sugarValue) {
			try {
				Thread.sleep(3000);
				raiseSugarLevel();
			} catch (InterruptedException ex) {
				Logger.getLogger(HumanBody.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	@Override
	public void run() {
		while (true) {

			this.idle();
			try {
				Thread.sleep(8000);
			} catch (InterruptedException ex) {
				Logger.getLogger(HumanBody.class.getName()).log(Level.SEVERE, null, ex);
			}

			//Config.sendEvent(new SugarMeasurementEvent(SugarLevel));
		}
	}

	private int random(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}

}
