package fi.thepaardihub.socket;

import java.util.Observable;
import java.util.Observer;

import fi.thepaardihub.models.Lobby;

public class UserLobbyObserver implements Observer{
	private Lobby lobby;
	private LobbySocket socket;
	private AnwserOptionsJSON options = null;
	
	public UserLobbyObserver(Lobby lobby, LobbySocket socket) {
		this.lobby = lobby;
		this.lobby.addObserver(this);
		this.socket = socket;
		
	}
	@Override
	public void update(Observable o, Object arg) {
		if(o == lobby && arg == null) {
			getAnwserOptions();
		}
		if(o==lobby) {
			
	
		}
		
	}
	
	
	private void getAnwserOptions() {
		options = lobby.getAwnserOptions();
	}
	
	private void setAnwser(PlayerInfo player) {
		lobby.setAnwser(player.getId(), player.getAnwser());
	}
	
	public AnwserOptionsJSON getOptions() {
		return options;
	}

}
