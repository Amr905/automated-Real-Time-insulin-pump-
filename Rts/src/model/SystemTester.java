package model;

import java.util.logging.Level;
import java.util.logging.Logger;

import event.SystemTesterEvent;
import main.Config;

public class SystemTester {
	public SystemTester(InsulinPumpSystem insulinPumpSystem) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					if (!insulinPumpSystem.checkPumperSensor())
						Config.sendEvent(new SystemTesterEvent("Error in insulin pumber sensor"));
					else if (!insulinPumpSystem.checkReservoir())
						Config.sendEvent(new SystemTesterEvent("Error in reservoir maybe it needs to be changed"));
					else if (!insulinPumpSystem.checkSugerMesurmentSensor())
						Config.sendEvent(new SystemTesterEvent("Error in sugar mesurment sensor"));
					else
						Config.sendEvent(new SystemTesterEvent("OK"));
					try {
						Thread.sleep(30000);// wait 30 sec
					} catch (InterruptedException ex) {
						Logger.getLogger(Clock.class.getName()).log(Level.SEVERE, null, ex);
					}
				}

			}

		}).start();
	}
}
