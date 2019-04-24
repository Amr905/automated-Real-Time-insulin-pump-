package model;

import java.util.logging.Level;
import java.util.logging.Logger;

import event.SystemTesterEvent;
import main.Config;

public class SystemTester {
	public SystemTester() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					Config.sendEvent(new SystemTesterEvent("OK"));// read sugar level value

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
