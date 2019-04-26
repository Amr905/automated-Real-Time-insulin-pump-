package model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Queue;

import view.PumpView;

public class Display {
	private int lastDose;
	private String msg;
	private LocalTime localTime;
	private ArrayList<String> bufferMsg;
	private PumpView GUI;

	public Display(PumpView GUI) {
		this.lastDose = 0;
		this.msg = "System Working";
		this.localTime = LocalTime.of(LocalTime.now().getHour(), LocalTime.now().getMinute());
		bufferMsg = new ArrayList<>();
		this.GUI = GUI;

	}

	public void displayClock(LocalTime Time) {
		this.localTime = Time;
		System.out.println(Time);
		GUI.SetClock(Time);
	}

	public void addMsg(String msg) {
		bufferMsg.add(msg);
		this.msg = msg;
		System.out.println(msg);
		GUI.SetMsg(msg);
	}

	public void displayLatestDose(int LastDose) {
		this.lastDose = LastDose;
		System.out.println("last dose -->"+LastDose);
		GUI.SetLastDose(LastDose);
	}

	public int getLateDose() {
		return lastDose;
	}

	public String getMsg() {
		return msg;
	}

	public LocalTime getLT() {
		return localTime;
	}

}
