package fi.thepaardihub.socket.jsonobject;

public class HostAction {

	private boolean startGame;
	private boolean getData;
	private boolean terminateLobby;
	private boolean internal;

	public boolean isStartGame() {
		return startGame;
	}

	public void setStartGame(boolean startGame) {
		this.startGame = startGame;
	}

	public boolean isGetData() {
		return getData;
	}

	public void setGetData(boolean getData) {
		this.getData = getData;
	}

	public boolean isTerminateLobby() {
		return terminateLobby;
	}

	public void setTerminateLobby(boolean terminateLobby) {
		this.terminateLobby = terminateLobby;
	}

	public boolean isInternal() {
		return internal;
	}

	public void setInternal(boolean internal) {
		this.internal = internal;
	}
	
}
