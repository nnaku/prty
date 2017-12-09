package fi.thepaardihub.socket;

import java.util.Observable;
import java.util.Observer;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import fi.thepaardihub.models.Lobby;
import fi.thepaardihub.socket.jsonobject.AnwserOptionsJSON;
import fi.thepaardihub.socket.jsonobject.HostAction;
import fi.thepaardihub.socket.jsonobject.LobbyJSON;
import fi.thepaardihub.socket.jsonobject.PlayerInfo;

@Controller
public class LobbySocket implements Observer {

	private Lobby lobby;

	public void setLobby(Lobby lobby) {
		this.lobby = lobby;

	}

	@SendTo("/lobby/testOut")
	@MessageMapping("/testIn")
	public String testInOut(String data) {
		return "You said " + data + "!";
	}

	@MessageMapping("/game")
	public void setAnwser(PlayerInfo player) {
		if (lobby != null)
			lobby.setAnwser(player);

	}

	@MessageMapping("/game/host")
	public void hostAction(HostAction action) {
		if (lobby != null)
			lobby.hostAction(action);

	}

	@Override
	public void update(Observable o, Object arg) {
		if (lobby != null) {
			if (o == lobby && arg == null) {
				getLobbyData();
				getOptions();

			}
			if (o == lobby) {
				getLobbyData();
			}

		}
	}

	@SendTo("/lobby/play")
	private AnwserOptionsJSON getOptions() {
		return lobby.getAwnserOptions();
	}

	@SendTo("/lobby/host/show")
	private LobbyJSON getLobbyData() {
		return lobby.getLobbyData();
	}
}
