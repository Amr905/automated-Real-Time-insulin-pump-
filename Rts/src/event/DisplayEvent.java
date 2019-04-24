package event;

import java.time.LocalTime;

public class DisplayEvent {
	private LocalTime clock;
	private String Msg;

	public DisplayEvent(LocalTime clock) {
		this.clock = clock;
	}

	public DisplayEvent(String msg) {
		this.Msg = msg;
	}

	public LocalTime getClock() {
		return clock;
	}

	public String getMsg() {
		return Msg;
	}

}
