package idv.tha101.extractp.base.pojo;

public enum MessageType {
	ENTER(0, "進入聊天室"), EXIT(1, "離開聊天室"), TALK(2, "發送消息"), PROFILE_REQUEST(3, "請求對方資料");

	private final int code;
	private final String name;

	MessageType(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
}