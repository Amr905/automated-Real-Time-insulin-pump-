package model;

import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;

import event.DisplayEvent;
import main.*;

public class Clock {

	public Clock() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				// Send Clock to Controller

				while (true) {
					LocalTime now = LocalTime.now();
					LocalTime MyTime = LocalTime.of(now.getHour(), now.getMinute());
					Config.sendEvent(new DisplayEvent(MyTime));
					try {
						Thread.sleep(60000);
					} catch (InterruptedException ex) {
						Logger.getLogger(Clock.class.getName()).log(Level.SEVERE, null, ex);
					}
				}

			}

		}).start();
	}
}
