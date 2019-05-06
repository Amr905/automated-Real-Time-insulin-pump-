package model;

import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;

import event.DisplayClockEvent;
import event.DisplayMsgEvent;
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
					LocalTime currentTime = LocalTime.of(now.getHour(), now.getMinute());
					System.out.println("Class Clock ---->" + currentTime);
					Config.sendEvent(new DisplayClockEvent(currentTime));
					if (currentTime.toString().equals("00:00")) {
						Config.sendEvent(new ResetEvent(true));
						Config.sendEvent(new DisplayMsgEvent("System Reseted"));
					}
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
