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
			if (player.getId().equals("internal")) {
				return lobby.getAwnserOptions();
			} else {
				lobby.setAnwser(player);
				return lobby.getAwnserOptions();
			}
		} else {
			return new AnwserOptionsJSON(LobbyState.LOBBY_NULL);
		}
	}

	

	@SendTo("/lobby/host/show")
	@MessageMapping("/game/host")
	public LobbyJSON hostAction(HostAction action) {
		if (lobby != null) {
			if (action.isInternal()) {
				return lobby.getLobbyData();
			} else {
				lobby.hostAction(action);
				return lobby.getLobbyData();
			}
		} else {
			return new LobbyJSON(LobbyState.LOBBY_NULL);
		}

	}

	@Override
	public void update(Observable o, Object arg) {

		if (lobby != null) {
			HostAction internalAction = new HostAction();
			internalAction.setGetData(false);
			internalAction.setInternal(true);
			internalAction.setStartGame(false);
			internalAction.setTerminateLobby(false);
			if (o == lobby && arg == null) {
				PlayerInfo internalPlayer = new PlayerInfo();
				internalPlayer.setAnwser("");
				internalPlayer.setId("");
				internalPlayer.setLeave(true);
				internalPlayer.setAnwser("");
				internalPlayer.setId("internal");
				internalPlayer.setLeave(true);
				hostAction(internalAction);
				setAnwser(internalPlayer);

			}
			if (o == lobby) {
				hostAction(internalAction);
			}

		}

	}

}
