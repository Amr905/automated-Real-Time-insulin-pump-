package model;

import java.util.logging.Level;
import java.util.logging.Logger;

import event.SystemTesterEvent;
import main.Config;

public class SystemTester {
	private boolean flag=true;
	private InsulinPumpSystem insulinPumpSystem;
	public SystemTester(InsulinPumpSystem insulinPumpSystem) {
		this.insulinPumpSystem=insulinPumpSystem;
		new Thread(new Runnable() {
			@Override
			public void run() {
				
				if(flag) {
					try {
						
						Thread.sleep(1500);
					} catch (InterruptedException ex) {
						Logger.getLogger(Clock.class.getName()).log(Level.SEVERE, null, ex);
					}
					flag=false;
				}
				
				while (true) {
					isSensorWorking();
					try {
						Thread.sleep(30000);// wait 30 sec
					} catch (InterruptedException ex) {
						Logger.getLogger(Clock.class.getName()).log(Level.SEVERE, null, ex);
					}
				}

			}

		}).start();
	}
	
	
	public void isSensorWorking() {
		if (!insulinPumpSystem.checkPumperSensor())
			Config.sendEvent(new SystemTesterEvent("Error in insulin pumber sensor"));
		else if (!insulinPumpSystem.checkReservoir())
			Config.sendEvent(new SystemTesterEvent("Change Resrvoir"));
		else if (!insulinPumpSystem.checkSugarMesurmentSensor())
			Config.sendEvent(new SystemTesterEvent("Error in sugar mesurment sensor"));
		else
			Config.sendEvent(new SystemTesterEvent("OK"));
	}
}
