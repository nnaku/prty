package fi.thepaardihub.socket.jsonobject;

import java.util.ArrayList;

import fi.thepaardihub.models.LobbyState;

public class AnwserOptionsJSON {

	private String option1;
	private String option2;
	private String option3;
	private String option4;
	private boolean playGame;
	private boolean takeAnwser;
	private int timer;
	private LobbyState state;

	public AnwserOptionsJSON(LobbyState state) {
		this.state = state;
		option1 = null;
		option2 = null;
		option3 = null;
		option4 = null;
		this.playGame = false;
		this.takeAnwser = false;
		this.timer = -404;
	}

	public AnwserOptionsJSON(ArrayList<String> options, boolean playGame, boolean takeAnwser, int timer,
			LobbyState state) {
		option1 = options.get(0);
		option2 = options.get(1);
		option3 = options.get(2);
		option4 = options.get(3);
		this.playGame = playGame;
		this.takeAnwser = takeAnwser;
		this.timer = timer;
		this.state = state;
	}

	public String getOption1() {
		return option1;
	}

	public String getOption2() {
		return option2;
	}

	public String getOption3() {
		return option3;
	}

	public String getOption4() {
		return option4;
	}

	public boolean getPlayGame() {
		return playGame;

	}

	public boolean isTakeAnwser() {
		return takeAnwser;
	}

	public int getTimer() {
		return timer;
	}

	public LobbyState getState() {
		return state;
	}

	public ArrayList<String> getOptions() {
		ArrayList<String> options = new ArrayList<>();
		options.add(option1);
		options.add(option2);
		options.add(option3);
		options.add(option4);
		return options;
	}

}
