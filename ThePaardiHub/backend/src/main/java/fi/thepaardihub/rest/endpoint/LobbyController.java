package fi.thepaardihub.rest.endpoint;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import fi.thepaardihub.services.LobbyService;
import fi.thepaardihub.rest.jsonobject.JoinInfo;
import fi.thepaardihub.rest.jsonobject.LobbyCreationInfo;
import fi.thepaardihub.security.JWT;

@RestController
@Controller
public class LobbyController {

	private LobbyService lobbyService;
	private JWT jwt = new JWT();
	private HashMap<String, Object> json;

	@Autowired
	public void setLobbyService(LobbyService lobbyService) {
		this.lobbyService = lobbyService;
	}

	@PostMapping("/createlobby")
	public ResponseEntity<?> createGame(@RequestHeader HttpHeaders headers, @RequestBody LobbyCreationInfo lobby) {
		json = new HashMap<String, Object>();
		String token = headers.getFirst("Authorization");
		if (jwt.validate(token).get("status") == "SUCCESS") {
			return new ResponseEntity<Object>(new Gson().toJson(lobbyService.createLobby(lobby.getId())), new HttpHeaders(),
					HttpStatus.OK);

		} else {
			json.put("status", "ERROR");
			json.put("message", jwt.validate(token));
			return new ResponseEntity<Object>(new Gson().toJson(json), HttpStatus.UNAUTHORIZED);
		}
	}
	
	@PostMapping("/addplayer")
	public ResponseEntity<?> addPlayer(@RequestBody JoinInfo player) {
		return new ResponseEntity<Object>(new Gson().toJson(lobbyService.addPlayer(player.getName(), player.getKey())), new HttpHeaders(),
				HttpStatus.OK);
	}
	
	@PostMapping("/lobbieexist")
	public ResponseEntity<?> lobbieExist(@RequestBody JoinInfo player) {
		return new ResponseEntity<Object>(new Gson().toJson(lobbyService.lobbyExist( player.getKey())), new HttpHeaders(),
				HttpStatus.OK);
	}

}
