/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.thepaardihub.rest.endpoint;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;

import fi.thepaardihub.dao.games.tables.Games;
import fi.thepaardihub.controllers.GameController;
import fi.thepaardihub.security.JWT;

/**
 *
 * @authors maaritemilia, akukangas
 */
@Controller
public class GamesControllerRest {

	private GameController games;
	private JWT jwt = new JWT();
	private HashMap<String, Object> json;

	@Autowired
	public void setGames(GameController games) {
		this.games = games;
	}
	
	
	@GetMapping("/gameset")
	public ResponseEntity<?> getGameById(@RequestHeader HttpHeaders headers, @RequestParam("gameId") String gameId) {
		json = new HashMap<String, Object>();
		String token = headers.getFirst("Authorization");
		if (jwt.validate(token).get("status") == "SUCCESS") {
			String author = (String) jwt.validate(token).get("email");
			try {
				Games game = games.getGame(Integer.parseInt(gameId), author);
				if (game != null) {
					json.put("status", "SUCCESS");
					json.put("game", game);
					return new ResponseEntity<Object>(new Gson().toJson(json), HttpStatus.OK);
				}
			} catch (Exception e) {
				e.printStackTrace();
				json.put("status", "ERROR");
				json.put("message", e);
				return new ResponseEntity<Object>(new Gson().toJson(json), HttpStatus.NOT_FOUND);
			}
		} else {
			json.put("status", "ERROR");
			json.put("message", jwt.validate(token));
			return new ResponseEntity<Object>(new Gson().toJson(json), HttpStatus.UNAUTHORIZED);
		}

		json.put("status", "ERROR");
		json.put("message", "Something went wrong :(");
		return new ResponseEntity<Object>(new Gson().toJson(json), HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/gamesets")
	public ResponseEntity<?> getGamesByAuthor(@RequestHeader HttpHeaders headers) {
		json = new HashMap<String, Object>();
		String token = headers.getFirst("Authorization");
		if (jwt.validate(token).get("status") == "SUCCESS") {
			String email = (String) jwt.validate(token).get("email");
			try {
				List<Games> game = games.getGameByAuthor(email);
				if (game != null) {
					json.put("status", "SUCCESS");
					json.put("game", game);
					return new ResponseEntity<Object>(new Gson().toJson(json), HttpStatus.OK);
				}
			} catch (Exception e) {
				e.printStackTrace();
				json.put("status", "ERROR");
				json.put("message", e);
				return new ResponseEntity<Object>(new Gson().toJson(json), HttpStatus.NOT_FOUND);
			}
		} else {
			json.put("status", "ERROR");
			json.put("message", jwt.validate(token));
			return new ResponseEntity<Object>(new Gson().toJson(json), HttpStatus.UNAUTHORIZED);
		}

		json.put("status", "ERROR");
		json.put("message", "Something went wrong :(");
		return new ResponseEntity<Object>(new Gson().toJson(json), HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/gameset")
	public ResponseEntity<?> createGame(@RequestHeader HttpHeaders headers, @RequestBody Map<String, Object> game) {
		json = new HashMap<String, Object>();
		String token = headers.getFirst("Authorization");
		if (jwt.validate(token).get("status") == "SUCCESS") {
			String author = (String) jwt.validate(token).get("email");
			try {
				Games newgame = games.createGame(author, (String)game.get("name"), (boolean) game.get("isPrivate"), (String) game.get("questions"), (String) game.get("description"));

				if (newgame != null) {
					json.put("stus", "SUCCESS");
					json.put("message", "Game saved succesfully");
					return new ResponseEntity<Object>(new Gson().toJson(json), HttpStatus.OK);
				}
			} catch (Exception e) {
				e.printStackTrace();
				json.put("status", "ERROR");
				json.put("message", e.getMessage());
				return new ResponseEntity<Object>(new Gson().toJson(json), HttpStatus.NOT_FOUND);
			}
		} else {
			json.put("status", "ERROR");
			json.put("message", jwt.validate(token));
			return new ResponseEntity<Object>(new Gson().toJson(json), HttpStatus.UNAUTHORIZED);
		}

		json.put("status", "ERROR");
		json.put("message", "Something went wrong :(");
		return new ResponseEntity<Object>(new Gson().toJson(json), HttpStatus.NOT_FOUND);
	}
	
}
