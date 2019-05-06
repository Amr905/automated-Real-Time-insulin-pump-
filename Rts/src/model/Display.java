package model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;

import view.PumpView;

public class Display {
	private int lastDose;
	private String msg;
	private LocalTime localTime;
	private PumpView GUI;
	private Queue<String> Buffer = new LinkedList<String>();

	public Display(PumpView GUI) {
		this.lastDose = 0;
		this.msg = "System Working";
		this.localTime = LocalTime.of(LocalTime.now().getHour(), LocalTime.now().getMinute());
		Buffer = new LinkedList<String>();
		this.GUI = GUI;
		Timer t = new Timer();
		t.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				if (!Buffer.isEmpty()) {
					String s = Buffer.remove();
					GUI.SetMsg(s);
				}

			}
		}, 1000, 5000);
	}

	public void displayClock(LocalTime Time) {
		this.localTime = Time;
		System.out.println(Time);
		GUI.SetClock(Time);
	}

	public void addMsg(String msg) {
		this.msg = msg;
		System.out.println(msg);
		Buffer.add(msg);
	}

	public void displayLastDose(int LastDose) {
		this.lastDose = LastDose;
		System.out.println("last dose -->" + LastDose);
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
