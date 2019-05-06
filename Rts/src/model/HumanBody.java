package model;

import java.math.RoundingMode;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import event.*;
import main.*;
import view.PumpView;

public class HumanBody implements Runnable {

	private int SugarLevel;
	private Integer lock;

	

	public int getSugarLevel() {
		return SugarLevel;
	}

	public HumanBody() {
		SugarLevel = 80;
		
	}

	public void raiseSugarLevel() {
		SugarLevel += random(5, 10);
	}

	public void DecSugarLevel() {
		SugarLevel -= random(5, 10);
	}

	public  void idle() {
		Random r = new Random();
		int rand = (int) Math.round(Math.random());
		if(SugarLevel<=60) {
			SugarLevel += random(2, 5);
		}
		else if (rand == 0) {
			SugarLevel += random(2, 5);
		} else if (rand == 1) {
			SugarLevel -= random(2, 5);
		}
		System.out.println("System Idle-->" + SugarLevel);
	}

	public synchronized void addInsulin(int insulinValue) {

		int currentSugarLevel = this.SugarLevel;
		while (SugarLevel > currentSugarLevel - insulinValue) {
			try {
				Thread.sleep(1000);
				this.DecSugarLevel();
				System.out.println("Insulin Added-->" + SugarLevel);
			} catch (InterruptedException ex) {
				Logger.getLogger(HumanBody.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	
}

	public synchronized void addSuger(int sugarValue) {
		int currentSugarLevel = this.SugarLevel;
		while (SugarLevel < currentSugarLevel + sugarValue) {
			try {
				Thread.sleep(1000);
				System.out.println("System AddSugar-->" + SugarLevel);
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
				Thread.sleep(10000);
			} catch (InterruptedException ex) {
				Logger.getLogger(HumanBody.class.getName()).log(Level.SEVERE, null, ex);
			}

			// Config.sendEvent(new SugarMeasurementEvent(SugarLevel));
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
