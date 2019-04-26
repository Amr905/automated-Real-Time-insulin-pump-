package event;

public class ReservoirEvent {

	private boolean ischanged;

	public ReservoirEvent(boolean ischanged) {
		this.ischanged = ischanged;
	}

	public boolean isChanged() {
		return ischanged;
	}

}
