
package main;

import java.time.LocalTime;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import model.HumanBody;
import model.InsulinPumpSystem;

public class Main {

	public static void main(String[] args) {

		// Disable logging
		Logger.getRootLogger().setLevel(Level.OFF);

		// Register events
		Config.registerEvents();
		
		final HumanBody humanBody = new HumanBody();
		Thread humanBodyThread = new Thread(humanBody);
		humanBodyThread.start();
		final InsulinPumpSystem insulinPumpSystem = new InsulinPumpSystem(humanBody);
		
		Config.createStatement("select clock from DisplayEvent").setSubscriber(new Object() {
			public void update(LocalTime clock) throws InterruptedException {
				insulinPumpSystem.Timer(clock);
			}
		});

		Config.createStatement("select sugarLevel from SugarMeasurementEvent").setSubscriber(new Object() {
			public void update(int SugarLevel) throws InterruptedException {
				insulinPumpSystem.addSugerMeasure(SugarLevel);
			}
		});
		Config.createStatement("select SystemStatus from FlowSensorEvent").setSubscriber(new Object() {
			public void update(String SystemStatus) throws InterruptedException {
				insulinPumpSystem.checkSystemSatus(SystemStatus);
			}
		});
		
		

	}

}
