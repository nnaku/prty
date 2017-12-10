package fi.thepaardihub.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fi.thepaardihub.dao.games.tables.Games;
import fi.thepaardihub.dao.games.tables.Questions;
import fi.thepaardihub.models.Lobby;
import fi.thepaardihub.models.Player;
import fi.thepaardihub.socket.LobbySocket;

@Service
public class LobbyService {

	private GameService games;
	private HashMap<String, Lobby> lobbies;

	private LobbySocket socket;

	@Autowired
	public LobbyService(GameService games, LobbySocket socket) {
		this.games = games;
		this.socket = socket;
		lobbies = new HashMap<String, Lobby>();
	}

	public  HashMap<String,String> createLobby(int GameSetId) {
		try {
			Games game = games.getGame(GameSetId);
			if (game != null) {
				ArrayList<Questions> questions = new ArrayList<>();
				String[] questionId = game.getQuestions().split("[;]");
				for (String s : questionId) {
					questions.add(games.getQuestions(Integer.parseInt(s)));

				}
				String lobbieKey;
				do {
			lobbieKey = "qwerty";
				}while(lobbies.containsKey(lobbieKey));
				Lobby lobby = new Lobby(game, questions, lobbieKey, this);
				socket.setLobby(lobby);
				lobbies.put(lobbieKey, lobby);

				Thread lobbyThread = new Thread(lobby);
				lobbyThread.start();
				HashMap<String, String> retVal = new HashMap<>();
				retVal.put("sendAddress", "/lobby/host/show");
				retVal.put("receiveAddress", "prty/game/host");
				// { "/lobby/host/show", "prty/game/host" };
				return retVal;
			} else {
				HashMap<String, String> retVal = new HashMap<>();
				retVal.put("error","error");
			return retVal;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	public HashMap<String, Boolean> lobbyExist(String key) {
		HashMap<String, Boolean> retVal = new HashMap<>();
		retVal.put("lobbieExist", lobbies.containsKey(key));
		return retVal;
	}
	
	public HashMap<String, String> addPlayer(String name, String lobbyKey) {
		if (lobbies.containsKey(lobbyKey)) {
			String playerID;
			do {
				playerID = UUID.randomUUID().toString();
			} while (lobbies.get(lobbyKey).containsPlayerId(playerID));
			lobbies.get(lobbyKey).addPlayer(playerID, new Player(name));
			HashMap<String, String> retVal = new HashMap<>();
			retVal.put("playerID", playerID);
			retVal.put("sendAddress", "/lobby/play");
			retVal.put("receiveAddress", "prty/game");
			// { playerID, "/lobby/play", "prty/game" };
			return retVal;
		} else {
			return null;
		}

	}
	public void removeLobby(String key) {
		lobbies.remove(key);
		socket.setLobby(null);
	}

}
