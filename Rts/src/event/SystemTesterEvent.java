package event;

public class SystemTesterEvent {
	private String SystemStatus;

	public SystemTesterEvent(String systemStatus) {
		SystemStatus = systemStatus;
	}

	public String getSystemStatus() {
		return SystemStatus;
	}

}
