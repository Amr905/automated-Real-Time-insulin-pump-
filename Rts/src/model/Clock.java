package model;

import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;

import event.DisplayClockEvent;
import event.ResetEvent;
import main.*;

public class Clock {

	public Clock() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				// Send Clock to Controller

				while (true) {
					LocalTime now = LocalTime.now();
					LocalTime myTime = LocalTime.of(now.getHour(), now.getMinute());
					System.out.println("Class Clock ---->"+myTime);
					Config.sendEvent(new DisplayClockEvent(myTime));
					if (myTime.toString().equals("00:00"))
						Config.sendEvent(new ResetEvent(true));
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
