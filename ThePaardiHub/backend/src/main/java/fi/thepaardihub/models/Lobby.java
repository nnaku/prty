package fi.thepaardihub.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Observable;
import java.util.concurrent.ThreadLocalRandom;

import fi.thepaardihub.dao.games.tables.Games;
import fi.thepaardihub.dao.games.tables.Questions;
import fi.thepaardihub.socket.jsonobject.AnwserOptionsJSON;
import fi.thepaardihub.socket.jsonobject.HostAction;
import fi.thepaardihub.socket.jsonobject.LobbyJSON;
import fi.thepaardihub.socket.jsonobject.PlayerInfo;

public class Lobby extends Observable implements Runnable {

	private Games gameData;
	private ArrayList<Questions> questions;
	private HashMap<String, Player> players;
	private Questions current;
	private String lobbyKey;
	private boolean terminate = false;
	private boolean playGame = false;
	private boolean takeAnwsers = false;
	private int timer = 0;
	private LobbyState state;

	public Lobby(Games gameData, ArrayList<Questions> questions, String lobbyKey) {
		this.gameData = gameData;
		this.questions = questions;
		this.lobbyKey = lobbyKey;
		players = new HashMap<String, Player>();
	}

	public void setAnwser(PlayerInfo player) {
		if (!player.isLeave()) {

			if (players.containsKey(player.getId()) && takeAnwsers) {
				players.get(player.getId()).setAnwser(player.getAnwser());
			}
		} else {
			removePlayer(player.getId());
		}

	}

	public boolean containsPlayerId(String id) {
		return players.containsKey(id);
	}

	public void addPlayer(String id, Player player) {
		if (!players.containsKey(id)) {
			players.put(id, player);
			setChanged();
			notifyObservers("SomeData");
		}
	}

	public void removePlayer(String id) {
		players.remove(id);
	}

	public AnwserOptionsJSON getAwnserOptions() {

		ArrayList<String> options = new ArrayList<>();

		options.add(current.getFalse1());
		options.add(current.getFalse2());
		options.add(current.getFalse3());
		options.add(current.getFalse4());
		options.add(current.getFalse5());
		options.add(current.getFalse6());
		options.add(current.getFalse7());

		while (options.remove(null)) {
		}
		while (options.size() > 3) {
			options.remove(ThreadLocalRandom.current().nextInt(0, options.size() + 1));
		}
		options.add(current.getCorrect());

		Collections.shuffle(options);
		AnwserOptionsJSON data = new AnwserOptionsJSON(options, playGame, takeAnwsers, timer, state);
		return data;

	}

	public LobbyJSON getLobbyData() {

		LobbyJSON data = new LobbyJSON();
		data.setPlayGame(playGame);
		data.setQuestion(current.getQuestion());
		data.setOptions(getAwnserOptions().getOptions());
		ArrayList<Player> playerData = new ArrayList<>();
		for (String s : players.keySet()) {
			playerData.add(players.get(s));
		}
		data.setPlayers(playerData);
		data.setLobbyKey(lobbyKey);
		data.setTakeAnwsers(takeAnwsers);
		data.setTimer(timer);
		data.setState(state);
		return data;

	}

	public void playGame() {
		int questionIndex = 0;
		Collections.shuffle(this.questions);

		while (questionIndex < questions.size()) {
			state = LobbyState.ASKING_QUESTION;
			this.current = questions.get(questionIndex);

			timer = 20;
			setChanged();
			notifyObservers();
			do {
				takeAnwsers = true;
				synchronized (this) {
					try {
						wait(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				timer--;
				setChanged();
				notifyObservers();

			} while ((timer < 0) && !allAnwsersGiven());

			takeAnwsers = false;
			checkCorrectAndReset();
			state = LobbyState.CHANING_QUESTION;
			timer = 5;
			
			do {

				synchronized (this) {
					try {
						wait(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				timer--;
				setChanged();
				notifyObservers();

			} while ((timer < 0));
			
			questionIndex++;

		}
	}

	private boolean allAnwsersGiven() {
		boolean retVal = true;
		for (String s : players.keySet()) {
			if (!players.get(s).getAnwser().equals("")) {
				retVal = false;
				break;
			}
		}
		return retVal;

	}

	private void checkCorrectAndReset() {
		for (String s : players.keySet()) {
			if (current.getCorrect().equals(players.get(s).getAnwser())) {
				players.get(s).setScore(players.get(s).getScore() + 10);
			}
			players.get(s).setAnwser("");
		}
	}

	@Override
	public void run() {
		while (!terminate) {
			state = LobbyState.GAME_READY;
			while (playGame) {
				playGame();
				state = LobbyState.GAME_FINISHED;
				playGame = false;
			}

		}

	}

	public void hostAction(HostAction action) {
		if (action.isStartGame()) {
			startGame();
		}
		if (action.isGetData()) {
			setChanged();
			notifyObservers("SomeData");
		}
		if (action.isTerminateLobby()) {
			terminate();
		}
	}

	public void startGame() {
		playGame = true;
	}

	public void terminate() {
		terminate = true;
		state = LobbyState.TERMINATING_LOBBY;
	}
}
