package fi.prty.socket.jsonobject;

import java.util.ArrayList;

import fi.prty.models.LobbyState;
import fi.prty.models.Player;

public class LobbyJSON {

	private String gameName;
	private String gameDescription;
	private String question;
	private ArrayList<String> options;
	private ArrayList<Player> players;
	private String lobbyKey;
	private String author;
	private boolean playGame;
	private boolean takeAnswers;
	private int timer;
	private LobbyState state;
	private int roundTime;
	private int pauseTime;

	public LobbyJSON() {

	}

	public LobbyJSON(LobbyState state) {
		this.state = state;
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

	public int getRoundTime() {
		return roundTime;
	}

	public void setRoundTime(int roundTime) {
		this.roundTime = roundTime;
	}

	public int getPauseTime() {
		return pauseTime;
	}

	public void setPauseTime(int pauseTime) {
		this.pauseTime = pauseTime;
	}

}
