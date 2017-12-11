package fi.thepaardihub.socket.jsonobject;

import java.util.ArrayList;

import fi.thepaardihub.models.LobbyState;
import fi.thepaardihub.models.Player;

public class LobbyJSON {
	
	
	private String gameName;
	private String gameDescription;
	private String question;
	private int nOfQuiz;
	private ArrayList<String> options;
	private ArrayList<Player> players;
	private String lobbyKey;
	private String author;
	private boolean playGame;
	private boolean takeAnswers;
	private int timer;
	private LobbyState state;

	public LobbyJSON() {

	}

	public LobbyJSON(LobbyState state) {
		this.state = state;
		options = null;
		gameName = null;
		gameDescription = null;
		players = null;
		lobbyKey = null;
		author = null;
		playGame = false;
		takeAnswers = false;
		timer = -404;
	}

	public String getLobbyKey() {
		return lobbyKey;
	}

	public void setLobbyKey(String lobbyKey) {
		this.lobbyKey = lobbyKey;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public ArrayList<String> getOptions() {
		return options;
	}

	public void setOptions(ArrayList<String> options) {
		this.options = options;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}

	public boolean isPlayGame() {
		return playGame;
	}

	public void setPlayGame(boolean playGame) {
		this.playGame = playGame;
	}

	public boolean isTakeAnswers() {
		return takeAnswers;
	}

	public void setTakeAnswers(boolean takeAnswers) {
		this.takeAnswers = takeAnswers;
	}

	public int getTimer() {
		return timer;
	}

	public void setTimer(int timer) {
		this.timer = timer;
	}

	public LobbyState getState() {
		return state;
	}

	public void setState(LobbyState state) {
		this.state = state;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getGameDescription() {
		return gameDescription;
	}

	public void setGameDescription(String gameDescription) {
		this.gameDescription = gameDescription;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public int getnOfQuiz() {
		return nOfQuiz;
	}

	public void setnOfQuiz(int nOfQuiz) {
		this.nOfQuiz = nOfQuiz;
	}

}
