package event;

public class SugarMeasurementEvent {

	private int sugarlevel;

	public SugarMeasurementEvent(int sugarlevel) {

		this.sugarlevel = sugarlevel;
	}

	public int getSugarLevel() {
		return this.sugarlevel;
	}

}
