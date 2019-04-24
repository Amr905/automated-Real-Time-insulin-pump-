package event;

import java.time.LocalTime;

public class DisplayClockEvent {
	private LocalTime clock;
	private String msg;

	public DisplayClockEvent(LocalTime clock) {
		this.clock = clock;
	}

	public DisplayClockEvent(String msg) {
		this.msg = msg;
	}

	public LocalTime getClock() {
		return clock;
	}

	public String getMsg() {
		return msg;
	}

}
