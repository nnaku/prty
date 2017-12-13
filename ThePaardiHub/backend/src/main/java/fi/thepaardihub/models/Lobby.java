package fi.thepaardihub.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Observable;
import java.util.concurrent.ThreadLocalRandom;

import fi.thepaardihub.dao.games.tables.Games;
import fi.thepaardihub.dao.games.tables.Questions;
import fi.thepaardihub.services.LobbyService;
import fi.thepaardihub.socket.jsonobject.AnswerOptionsJSON;
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
	private boolean takeAnswers = false;
	private final int ROUND_TIME = 30;
	private final int PAUSE_TIME = 10;
	private int timer;
	private LobbyState state;
	private LobbyService service;
	private AnswerOptionsJSON anwserOptions;

	private final int ANWSER_TIER1 = 250;
	private final int ANWSER_TIER2 = 100;
	private final int ANWSER_TIER3 = 50;
	private final int ANWSER_TIER4 = 25;
	private final int ANWSER_TIER5 = 10;

	public Lobby(Games gameData, ArrayList<Questions> questions, String lobbyKey, LobbyService service) {

		this.service = service;
		this.gameData = gameData;
		this.questions = questions;
		this.current = questions.get(0);
		this.lobbyKey = lobbyKey;
		generateAnswerOptions();
		players = new HashMap<String, Player>();
	}

	public synchronized void setAnswer(PlayerInfo player) {
		if (!player.isLeave()) {

			if (players.containsKey(player.getId()) && takeAnswers) {
				players.get(player.getId()).setAnswer(player.getAnswer());
				players.get(player.getId()).setAnwserTime(timer);
			}
		} else {
			removePlayer(player.getId());
		}

	}

	public synchronized boolean containsPlayerId(String id) {
		return players.containsKey(id);
	}

	public synchronized void addPlayer(String id, Player player) {
		if (!players.containsKey(id)) {
			players.put(id, player);
			setChanged();
			notifyObservers();
		}
	}

	private void removePlayer(String id) {
		players.remove(id);
	}

	public synchronized AnswerOptionsJSON getAwnserOptions() {
		return this.anwserOptions;
	}

	private void generateAnswerOptions() {

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

		this.anwserOptions = new AnswerOptionsJSON(options, playGame, takeAnswers, timer, state);

	}

	public synchronized LobbyJSON getLobbyData() {

		LobbyJSON data = new LobbyJSON();
		data.setAuthor(gameData.getAuthor());
		data.setPauseTime(this.PAUSE_TIME);
		data.setRoundTime(this.ROUND_TIME);
		data.setGameName(gameData.getGameName());
		data.setGameDescription(gameData.getDescription());
		data.setPlayGame(playGame);
		data.setQuestion(current.getQuestion());
		data.setOptions(getAwnserOptions().getOptions());
		ArrayList<Player> playerData = new ArrayList<>();
		for (String s : players.keySet()) {
			playerData.add(players.get(s));
		}
		data.setPlayers(playerData);
		data.setLobbyKey(lobbyKey);
		data.setTakeAnswers(takeAnswers);
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
			generateAnswerOptions();
			timer = ROUND_TIME;
			do {
				setChanged();
				notifyObservers();
				takeAnswers = true;
				this.anwserOptions.setTakeAnswer(takeAnswers);
				synchronized (this) {
					try {
						timer--;
						wait(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} while ((timer > 0) && !allAnswersGiven());

			takeAnswers = false;
			this.anwserOptions.setTakeAnswer(takeAnswers);
			checkCorrectAndReset();
			state = LobbyState.CHANING_QUESTION;
			this.anwserOptions.setState(state);
			timer = PAUSE_TIME;
			do {
				setChanged();
				notifyObservers();
				synchronized (this) {
					try {
						timer--;
						wait(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} while ((timer > 0));

			questionIndex++;

		}
	}

	private boolean allAnswersGiven() {
		for (String s : players.keySet()) {
			if (players.get(s).getAnswer().equals("")) {
				return false;
			}
		}
		return true;
	}

	private void checkCorrectAndReset() {

		for (String s : players.keySet()) {
			if (current.getCorrect().equals(players.get(s).getAnswer())) {
				if (players.get(s).getAnwserTime() >= 20) {
					players.get(s).setScore(players.get(s).getScore() + ANWSER_TIER1);
					players.get(s).setAddedPoints(ANWSER_TIER1);

				} else if (players.get(s).getAnwserTime() >= 15) {
					players.get(s).setScore(players.get(s).getScore() + ANWSER_TIER2);
					players.get(s).setAddedPoints(ANWSER_TIER2);

				} else if (players.get(s).getAnwserTime() >= 10) {
					players.get(s).setScore(players.get(s).getScore() + ANWSER_TIER3);
					players.get(s).setAddedPoints(ANWSER_TIER3);
				} else if (players.get(s).getAnwserTime() >= 5) {
					players.get(s).setScore(players.get(s).getScore() + ANWSER_TIER4);
					players.get(s).setAddedPoints(ANWSER_TIER4);
					
				} else {
					players.get(s).setScore(players.get(s).getScore() + ANWSER_TIER5);
					players.get(s).setAddedPoints(ANWSER_TIER5);
				}

			}
			players.get(s).setAnwserTime(0);
			players.get(s).setAnswer("");

		}
	}

	@Override
	public void run() {
		while (!terminate) {
			if (state != LobbyState.GAME_FINISHED) {
				synchronized (this) {
					try {
						state = LobbyState.GAME_READY;
						this.anwserOptions.setState(state);
						setChanged();
						notifyObservers();
						wait(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				while (playGame) {
					playGame();
					synchronized (this) {
						playGame = false;
						state = LobbyState.GAME_FINISHED;
						this.anwserOptions.setState(state);
						setChanged();
						notifyObservers();

					}
				}
			} else {
				// auto close/terminate lobby
				try {
					wait(30000);
					terminate();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		service.removeLobby(lobbyKey);

	}

	public void hostAction(HostAction action) {
		if (action.isStartGame()) {
			startGame();
		}
		if (action.isGetData()) {
			setChanged();
			notifyObservers();
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
		setChanged();
		notifyObservers();
		synchronized(this) {
			try {
				wait(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.anwserOptions.setState(state);
	}
}
