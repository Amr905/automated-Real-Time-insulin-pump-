package model;

import event.DisplayLastDose;
import main.Config;

public class InsulinPumper {
	HumanBody humanBody;

	public InsulinPumper(HumanBody humanBody) {
		this.humanBody = humanBody;
	}

	public void pumpInsulin(int insulinValue) {
		humanBody.addInsulin(insulinValue);
		System.out.println("InsulinPumper--->Pumped--->"+insulinValue);
		Config.sendEvent(new DisplayLastDose(insulinValue));
	}
}
