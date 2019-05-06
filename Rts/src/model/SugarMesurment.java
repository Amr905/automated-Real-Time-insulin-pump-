package model;

import java.util.logging.Level;
import java.util.logging.Logger;

import event.SugarMeasurementEvent;
import main.Config;

public class SugarMesurment {
	
	private boolean flag = true;
	public SugarMesurment(HumanBody humanBody) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					if(flag) {
						try {
							
							Thread.sleep(1500);
						} catch (InterruptedException ex) {
							Logger.getLogger(Clock.class.getName()).log(Level.SEVERE, null, ex);
						}
						flag=false;
					}
						
					Config.sendEvent(new SugarMeasurementEvent(humanBody.getSugarLevel()));// read sugar level value
					System.out.println("SaveSugarLevel--->" + humanBody.getSugarLevel());

					try {
						
						Thread.sleep(30000);// wait 30 sec
					} catch (InterruptedException ex) {
						Logger.getLogger(Clock.class.getName()).log(Level.SEVERE, null, ex);
					}
				}

			}

		}).start();
	}
	public boolean checkSensor() {
		return true;
	}
}
