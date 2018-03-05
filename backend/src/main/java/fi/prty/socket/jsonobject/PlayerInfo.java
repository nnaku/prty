package fi.prty.socket.jsonobject;

public class PlayerInfo {

	private String id;
	private String answer;
	private boolean leave = false;
	
	public boolean isLeave() {
		return leave;
	}

	public void setLeave(boolean leave) {
		this.leave = leave;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

}
