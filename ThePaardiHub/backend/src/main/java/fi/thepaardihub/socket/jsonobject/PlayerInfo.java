package fi.thepaardihub.socket.jsonobject;

public class PlayerInfo {

	private String id;
	private String anwser;
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

	public String getAnwser() {
		return anwser;
	}

	public void setAnwser(String anwser) {
		this.anwser = anwser;
	}

}
