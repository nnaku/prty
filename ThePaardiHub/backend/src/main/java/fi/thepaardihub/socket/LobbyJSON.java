package fi.thepaardihub.socket;

import java.util.ArrayList;

import fi.thepaardihub.models.Player;

public class LobbyJSON {
	
	private String question;
	private ArrayList<String> options;
	private ArrayList<Player> players;
	private String lobbyKey;
	private String author;
	private boolean playGame;
	
	
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
	
	
	
	
	

}
