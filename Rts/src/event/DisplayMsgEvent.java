package event;

public class DisplayMsgEvent {
	private String msg;

	public DisplayMsgEvent(String msg) {

		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}
}
