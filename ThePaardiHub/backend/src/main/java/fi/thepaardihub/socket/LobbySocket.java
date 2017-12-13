package fi.thepaardihub.socket;

import java.util.Observable;
import java.util.Observer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import fi.thepaardihub.models.Lobby;
import fi.thepaardihub.models.LobbyState;
import fi.thepaardihub.socket.jsonobject.AnswerOptionsJSON;
import fi.thepaardihub.socket.jsonobject.HostAction;
import fi.thepaardihub.socket.jsonobject.LobbyJSON;
import fi.thepaardihub.socket.jsonobject.PlayerInfo;

@Controller
public class LobbySocket implements Observer {

	private Lobby lobby;

	@Autowired
	private SimpMessagingTemplate template;

	public void setLobby(Lobby lobby) {
		this.lobby = lobby;
		this.lobby.addObserver(this);

	}

	@SendTo("/lobby/play")
	@MessageMapping("/game")
	public AnswerOptionsJSON setAnswer(PlayerInfo player) {

		if (lobby != null) {

			lobby.setAnswer(player);
			synchronized (this) {
				try {
					wait(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			return lobby.getAwnserOptions();

		} else {
			return new AnswerOptionsJSON(LobbyState.LOBBY_NULL);
		}
	}

	@SendTo("/lobby/host/show")
	@MessageMapping("/game/host")
	public LobbyJSON hostAction(HostAction action) {
		if (lobby != null) {

			lobby.hostAction(action);
			synchronized (this) {
				try {
					wait(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			return lobby.getLobbyData();

		} else {
			return new LobbyJSON(LobbyState.LOBBY_NULL);
		}

	}

	@Override
	public void update(Observable o, Object arg) {

		if (lobby != null) {
			if (o == lobby) {
				this.template.convertAndSend("/lobby/host/show", lobby.getLobbyData());
				this.template.convertAndSend("/lobby/play", lobby.getAwnserOptions());

			}

		}

	}

}
