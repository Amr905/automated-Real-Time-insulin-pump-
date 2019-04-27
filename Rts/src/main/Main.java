
package main;

import java.time.LocalTime;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import event.DisplayClockEvent;
import event.ReservoirEvent;
import model.Clock;
import model.HumanBody;
import model.InsulinPumpSystem;
import view.PumpView;

public class Main{

	public static void main(String[] args) {
		final HumanBody humanBody = new HumanBody();

		Thread humanBodyThread = new Thread(humanBody);
		humanBodyThread.start();
		Thread main = new Thread(new Runnable() {
			@Override
			public void run() {
				
				Program(humanBody);
			}
		});
		main.start();
		

	}
	
	public static void Program(HumanBody humanBody) {
		// Disable logging
		Logger.getRootLogger().setLevel(Level.OFF);

		// Register events
		Config.registerEvents();


		final InsulinPumpSystem insulinPumpSystem = new InsulinPumpSystem(humanBody);

		Config.createStatement("select clock from DisplayClockEvent").setSubscriber(new Object() {
			public void update(LocalTime clock) throws InterruptedException {
				// System.out.println("Clock-->"+clock);
				insulinPumpSystem.displayTime(clock);
			}
		});
		Config.createStatement("select sugarLevel from SugarMeasurementEvent").setSubscriber(new Object() {
			public void update(int SugarLevel) throws InterruptedException {
				insulinPumpSystem.saveCurrentSugarMeasure(SugarLevel);
			}
		});
		Config.createStatement("select systemStatus from SystemTesterEvent").setSubscriber(new Object() {
			public void update(String systemstatus) throws InterruptedException {
				insulinPumpSystem.checkSystemSatus(systemstatus);
			}
		});
		Config.createStatement("select msg from DisplayMsgEvent").setSubscriber(new Object() {
			public void update(String msg) throws InterruptedException {
				insulinPumpSystem.displayMsg(msg);
			}
		});
		Config.createStatement("select changed from ReservoirEvent").setSubscriber(new Object() {
			public void update(boolean ischanged) throws InterruptedException {
				insulinPumpSystem.changeReservoir(ischanged);
			}
		});
		Config.createStatement("select dose from DisplayLastDose").setSubscriber(new Object() {
			public void update(int dose) throws InterruptedException {
				System.out.println("EPLStatementLateDose--->" + dose);
				insulinPumpSystem.displayLastDose(dose);
			}
		});
		Config.createStatement("select insulinvalue from PumpEvent").setSubscriber(new Object() {
			public void update(int insulinvalue) throws InterruptedException {

				insulinPumpSystem.pumpInsluin(insulinvalue);
			}
		});
		Config.createStatement("select isReset from ResetEvent").setSubscriber(new Object() {
			public void update(boolean isReset) throws InterruptedException {
				insulinPumpSystem.resetSystem();
			}
		});
	}



	

}
