package fi.thepaardihub.rest.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fi.thepaardihub.controllers.LobbyService;
import fi.thepaardihub.rest.jsonobject.JoinInfo;
import fi.thepaardihub.rest.jsonobject.LobbyCreationInfo;

@RestController
@Controller
public class LobbyController {
	
	private LobbyService lobbyService;
	
	@Autowired
	public void setLobbyService(LobbyService lobbyService) {
		this.lobbyService = lobbyService;
	}
	
	@PostMapping("/createlobby")
	public ResponseEntity<?> createGame(@RequestBody LobbyCreationInfo lobby) {
		return new ResponseEntity<Object>(lobbyService.createLobby(lobby.getId()), new HttpHeaders(), HttpStatus.OK);
	}
	@PostMapping("/addplayer")
	public ResponseEntity<?> addPlayer(@RequestBody JoinInfo player){
		return new ResponseEntity<Object>(lobbyService.addPlayer(player.getName(), player.getKey()), new HttpHeaders(), HttpStatus.OK);
	}

}
