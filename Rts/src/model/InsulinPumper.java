package model;

public class InsulinPumper {
	HumanBody humanBody;

	public InsulinPumper(HumanBody humanBody) {
		this.humanBody = humanBody;
	}

	public void pumpInsulin(int insulinValue) {
		humanBody.addInsulin(insulinValue);
	}
}
