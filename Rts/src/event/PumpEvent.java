package event;

public class PumpEvent {

	private int insulinvalue;

	public PumpEvent(int PumpState) {
		this.insulinvalue = PumpState;
	}

	public int getInsulinvalue() {
		return insulinvalue;
	}
}
