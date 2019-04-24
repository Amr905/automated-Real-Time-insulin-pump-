package model;

import java.time.LocalTime;

import event.DisplayClockEvent;
import main.Config;

public class InsulinPumpSystem {

	private Clock clock;
	private Display MyDisplay;
	private SugarMesurment sugarMesurment;
	private SystemTester St;
	private int [] sugerReading;
	private int ReadingIndex;

	public InsulinPumpSystem(HumanBody humanBody) {
		this.clock = new Clock();
		this.MyDisplay = new Display();
		this.St=new SystemTester();
		this.sugarMesurment = new SugarMesurment(humanBody);
		sugerReading=new int[3];
		ReadingIndex=0;
		
	}

	public void Timer(LocalTime time) {
		this.MyDisplay.displayClock(time);
	}
	public void SystemError(String s) {
		this.MyDisplay.displayError(s);
	}

	public void checkSystemSatus(String status) {
		if (!status.equals("OK")) {
			Config.sendEvent(new DisplayClockEvent("ERROR CLOSING"));
			// Stop();
		}
		/*else {
			Config.sendEvent(new DisplayEvent("System Working"));
		}*/
			
	}
	
	public void addSugerMeasure(int sugerReading) {
		this.sugerReading[ReadingIndex]=sugerReading;
		ReadingIndex=(ReadingIndex+1)%3;
		//if(ReadingIndex==0)
			//do something
		
	}
}
