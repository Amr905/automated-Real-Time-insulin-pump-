package model;

import java.util.logging.Level;
import java.util.logging.Logger;

import event.SugarMeasurementEvent;
import main.Config;

public class SugarMesurment {
	public SugarMesurment(HumanBody humanBody) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					Config.sendEvent(new SugarMeasurementEvent(humanBody.getSugarLevel()));// read sugar level value

					try {
						System.out.println("Added Sugar Level--->" + humanBody.getSugarLevel());
						Thread.sleep(30000);// wait 10 sec
					} catch (InterruptedException ex) {
						Logger.getLogger(Clock.class.getName()).log(Level.SEVERE, null, ex);
					}
				}

			}

		}).start();
	}
}
