package fi.thepaardihub.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Observable;
import java.util.concurrent.ThreadLocalRandom;

import fi.thepaardihub.dao.games.tables.Games;
import fi.thepaardihub.dao.games.tables.Questions;
import fi.thepaardihub.socket.LobbyJSON;

public class Lobby extends Observable {

	private Games gameData;
	private ArrayList<Questions> questions;
	private HashMap<String, Player> players;
	private Questions current;
	private String lobbyKey;
	
	public Lobby(Games gameData, ArrayList<Questions> questions, String lobbyKey){
		this.gameData = gameData;
		this.questions = questions;
		this.lobbyKey = lobbyKey;
		players = new HashMap<String, Player>();
	}

	public void getAnwser(String id, String anwser) {
		players.get(id).setAnwser(anwser);

	}

	public void addPlayer(String id, Player player) {
		players.put(id, player);
	}

	public void removePlayer(String id) {
		players.remove(id);
	}

	public ArrayList<String> getAwnserOptions() {

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
		return options;

	}

	public LobbyJSON getLobbyData() {

		LobbyJSON data = new LobbyJSON();

		data.setQuestion(current.getQuestion());
		data.setOptions(getAwnserOptions());
		ArrayList<Player> playerData = new ArrayList<>();
		for (String e : players.keySet()) {
			playerData.add(players.get(e));
		}
		data.setPlayers(playerData);
		data.setLobbyKey(lobbyKey);
		return data;

	}

	public void playGame() {
		int questionIndex = 0;
		Collections.shuffle(this.questions);

		while (questionIndex < questions.size()) {
			this.current = questions.get(questionIndex);
			setChanged();
			notifyObservers();
			
			
			
			

		}
	}

}
