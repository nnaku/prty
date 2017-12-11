package fi.thepaardihub.models;

public enum LobbyState {

	STARTING_LOBBY(10), 
	TERMINATING_LOBBY(15), 
	GAME_READY(20),
	GAME_NOT_READY(19),
	GAME_FINISHED(25),
	ASKING_QUESTION(30),
	CHANING_QUESTION(35),
	LOBBY_NULL(44);

	private int state;

	private LobbyState(int state) {
		this.state = state;
	}

	public int state() {
		return this.state;
	}
}
