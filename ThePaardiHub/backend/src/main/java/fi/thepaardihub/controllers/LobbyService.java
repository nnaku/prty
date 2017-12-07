package fi.thepaardihub.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fi.thepaardihub.dao.games.tables.Games;
import fi.thepaardihub.dao.games.tables.Questions;
import fi.thepaardihub.models.Lobby;
import fi.thepaardihub.socket.LobbySocket;

@Service
public class LobbyService {
	
	private GameController games;
	private HashMap<String,Lobby> lobbies;
	
	private LobbySocket socket;
	
	@Autowired
	public LobbyService(GameController games,LobbySocket socket) {
		this.games = games;
		this.socket = socket;
		lobbies = new HashMap<String, Lobby>();
	}
	
	public String[] createLobby(int GameSetId) {
		Games game = games.getGame(GameSetId);
		ArrayList<Questions> questions = new ArrayList<>();
		String[] questionId = game.getQuestions().split("[;]");
		for(String s : questionId) {
			questions.add(games.getQuestions(Integer.parseInt(s)));
			
		}
		String lobbieKey = "qwerty";
		
		Lobby lobby = new Lobby(game,questions,lobbieKey);
		socket.setLobby(lobby);
		lobbies.put(lobbieKey, lobby);
		
		Thread lobbyThread = new Thread(lobby);
		lobbyThread.start();
		String[] retVal = {"/game/host", "/lobby/host/show"};
		return retVal;
	}
	
	
	public String[] addPlayer(String name, String lobbyKey) {
		
		String playerID;
		do {
			playerID = UUID.randomUUID().toString();
		}while(lobbies.get(lobbyKey).containsPlayerId(playerID));
		
 		String[] retVal = {playerID, "/lobby/play", "/game"};
		return retVal;
		
	}

}