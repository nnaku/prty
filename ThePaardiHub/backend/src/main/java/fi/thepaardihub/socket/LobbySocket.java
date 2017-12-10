package fi.thepaardihub.socket;

import java.util.Observable;
import java.util.Observer;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import fi.thepaardihub.models.Lobby;
import fi.thepaardihub.models.LobbyState;
import fi.thepaardihub.socket.jsonobject.AnwserOptionsJSON;
import fi.thepaardihub.socket.jsonobject.HostAction;
import fi.thepaardihub.socket.jsonobject.LobbyJSON;
import fi.thepaardihub.socket.jsonobject.PlayerInfo;

@Controller
public class LobbySocket implements Observer {

	private Lobby lobby;

	public void setLobby(Lobby lobby) {
		this.lobby = lobby;
		this.lobby.addObserver(this);

	}

	@SendTo("/lobby/testOut")
	@MessageMapping("/testIn")
	public String testInOut(String data) {
		return "You said " + data + "!";
	}
	@SendTo("/lobby/play")
	@MessageMapping("/game")
	public AnwserOptionsJSON setAnwser(PlayerInfo player) {
		if (lobby != null) {
			lobby.setAnwser(player);
			return lobby.getAwnserOptions();
		} else {
			return new AnwserOptionsJSON(LobbyState.LOBBY_NULL);
		}

	}
	@SendTo("/lobby/host/show")
	@MessageMapping("/game/host")
	public LobbyJSON hostAction(HostAction action) {
		if (lobby != null) {
			lobby.hostAction(action);
			return lobby.getLobbyData();
		} else {
			return new LobbyJSON(LobbyState.LOBBY_NULL);
		}

	}

	@Override
	public void update(Observable o, Object arg) {
		if (lobby != null) {
			if (o == lobby && arg == null) {
//				hostAction();
//				setAnwser();

			}
			if (o == lobby) {
//				hostAction();
			}

		}

	}

	
}
