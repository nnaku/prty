package fi.thepaardihub.socket;

public class HostAction {

	private boolean startGame;
	private boolean getData;
	private boolean terminateLobby;

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
	
}