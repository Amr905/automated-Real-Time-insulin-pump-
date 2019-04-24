package event;

public class PumpEvent {

	private boolean PumpState;

	public PumpEvent(boolean PumpState) {
		this.PumpState = PumpState;
	}

	public boolean getPumpState() {
		return PumpState;
	}
}
