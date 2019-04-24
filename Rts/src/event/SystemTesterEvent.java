package event;

public class SystemTesterEvent {
	private String systemstatus;

	public SystemTesterEvent(String systemstatus) {
		this.systemstatus = systemstatus;
	}

	public String getSystemStatus() {
		return systemstatus;
	}

}
