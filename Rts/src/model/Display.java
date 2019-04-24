package model;

import java.time.LocalTime;

public class Display {
	private int LateDose;
	private String Msg;
	private LocalTime LT;

	public Display() {
		this.LateDose=0;
		this.Msg="System Working";
		this.LT=LocalTime.of(LocalTime.now().getHour(), LocalTime.now().getMinute());
	}

	public void displayClock(LocalTime Time) {
		this.LT=Time;
		System.out.println(Time);
	}
	public void displayError (String s) {
		this.Msg=s;
		System.out.println(s);
	}
	public void displayLatestDose(int LateDose) {
		this.LateDose=LateDose;
		System.out.println(LateDose);
	}
	public int getLateDose() {
		return LateDose;
	}
	public String getMsg() {
		return Msg;
	}
	public LocalTime getLT() {
		return LT;
	}


	

}
