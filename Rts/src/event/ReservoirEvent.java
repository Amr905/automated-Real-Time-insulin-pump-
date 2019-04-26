package event;

public class ReservoirEvent {

	private boolean ischanged;

	public ReservoirEvent(boolean isChanged) {
		this.ischanged = isChanged;
	}

	public boolean isChanged() {
		return ischanged;
	}

}
