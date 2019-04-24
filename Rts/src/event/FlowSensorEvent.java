package event;

public class FlowSensorEvent {
	private int PumpedInsulin;

	public FlowSensorEvent(int pumpedInsulin) {

		this.PumpedInsulin = pumpedInsulin;
	}

	public int getPumpedInsulin() {
		return PumpedInsulin;
	}

}
