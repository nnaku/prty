package fi.thepaardihub.socket.jsonobject;

import java.util.ArrayList;

import fi.thepaardihub.models.LobbyState;

public class AnswerOptionsJSON {

	private boolean playGame;
	private boolean takeAnswer;
	private LobbyState state;
	private ArrayList<String> options;

	public AnswerOptionsJSON(LobbyState state) {
		this.state = state;
		this.playGame = false;
		this.takeAnswer = false;
	}

	public AnswerOptionsJSON(ArrayList<String> options, boolean playGame, boolean takeAnswer, int timer,
			LobbyState state) {
		// option1 = options.get(0);
		// option2 = options.get(1);
		// option3 = options.get(2);
		// option4 = options.get(3);
		this.playGame = playGame;
		this.takeAnswer = takeAnswer;
		this.state = state;
		this.options = options;
	}

	public boolean getPlayGame() {
		return playGame;

	}

	public boolean isTakeAnswer() {
		return takeAnswer;
	}


	public LobbyState getState() {
		return state;
	}

	public ArrayList<String> getOptions() {
		return options;
	}

	public void setPlayGame(boolean playGame) {
		this.playGame = playGame;
	}

	public void setTakeAnswer(boolean takeAnswer) {
		this.takeAnswer = takeAnswer;
	}

	public void setState(LobbyState state) {
		this.state = state;
	}

	public void setOptions(ArrayList<String> options) {
		this.options = options;
	}
	

}
