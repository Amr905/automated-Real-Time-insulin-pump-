package model;

import java.util.logging.Level;
import java.util.logging.Logger;

import event.FlowSensorEvent;
import main.Config;

public class FlowSensor {
	public FlowSensor() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					Config.sendEvent(new FlowSensorEvent(11));// read sugar level value

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
