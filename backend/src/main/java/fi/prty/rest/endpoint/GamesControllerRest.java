/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.prty.rest.endpoint;

import java.util.ArrayList;
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

import fi.prty.dao.games.tables.Games;
import fi.prty.dao.games.tables.Questions;
import fi.prty.security.JWT;
import fi.prty.services.GameService;

/**
 *
 * @authors maaritemilia, akukangas
 */
@Controller
public class GamesControllerRest {

	private GameService games;
	private JWT jwt = new JWT();
	private HashMap<String, Object> json;

	@Autowired
	public void setGames(GameService games) {
		this.games = games;
	}

	@GetMapping("/gameset")
	public ResponseEntity<?> getGameById(@RequestHeader HttpHeaders headers, @RequestParam("id") String id) {
		json = new HashMap<String, Object>();
		String token = headers.getFirst("Authorization");
		if (jwt.validate(token).get("status") == "SUCCESS") {
			String author = (String) jwt.validate(token).get("email");
			try {
				Games game = games.getGame(Integer.parseInt(id), author);
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
			String author = (String) jwt.validate(token).get("email");
			try {
				List<Games> game = games.getGameByAuthor(author);
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
	public ResponseEntity<?> createGame(@RequestHeader HttpHeaders headers, @RequestBody Map<String, String> game) {
		json = new HashMap<String, Object>();
		String token = headers.getFirst("Authorization");
		if (jwt.validate(token).get("status") == "SUCCESS") {
			String author = (String) jwt.validate(token).get("email");
			try {
				Games newGame = games.createGame(author, game.get("gameName"), true, game.get("questions"),game.get("description"));

				if (newGame != null) {

					json.put("stus", "SUCCESS");
					json.put("message", "Game saved succesfully");
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

	@PostMapping("/question")
	public ResponseEntity<?> createQuestion(@RequestHeader HttpHeaders headers,
			@RequestBody Map<String, Object> question) {
		json = new HashMap<String, Object>();
		String token = headers.getFirst("Authorization");
		if (jwt.validate(token).get("status") == "SUCCESS") {
			String author = (String) jwt.validate(token).get("email");
			try {

				String[] wrong = new String[7];
				// purkka!
				wrong[0] = (String) question.get("wrong1");
				wrong[1] = (String) question.get("wrong2");
				wrong[2] = (String) question.get("wrong3");
				wrong[3] = (String) question.get("wrong4");
				wrong[4] = (String) question.get("wrong5");
				wrong[5] = (String) question.get("wrong6");
				wrong[6] = (String) question.get("wrong7");

				Questions newQuestion = games.createQuestion(author, (String) question.get("question"),
						(String) question.get("correct"), wrong, (boolean) question.get("privat"));

				if (newQuestion != null) {
					json.put("stus", "SUCCESS");
					json.put("message", "Question saved succesfully");
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

	@GetMapping("/question")
	public ResponseEntity<?> getQuestionById(@RequestHeader HttpHeaders headers, @RequestParam("id") String id) {
		json = new HashMap<String, Object>();
		String token = headers.getFirst("Authorization");
		if (jwt.validate(token).get("status") == "SUCCESS") {
			String author = (String) jwt.validate(token).get("email");
			try {
				String[] ids = id.split(";");
				ArrayList<Questions> questions = new ArrayList<>();
				Questions question = new Questions();
				for (int i = 0; i < ids.length; i++) {
					question = games.getQuestions(Integer.parseInt(ids[i]), author);
					if (question != null) {
						questions.add(question);
					}
				}

				if (questions.size() < 1) {
					json.put("status", "ERROR");
					json.put("message", "No questoins found!");
				} else {
					json.put("status", "SUCCESS");
					json.put("questions", questions);
				}

				return new ResponseEntity<Object>(new Gson().toJson(json), HttpStatus.OK);

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
	}

	@GetMapping("/questions")
	public ResponseEntity<?> getQuestionsByAuthor(@RequestHeader HttpHeaders headers) {
		json = new HashMap<String, Object>();
		String token = headers.getFirst("Authorization");
		if (jwt.validate(token).get("status") == "SUCCESS") {
			String author = (String) jwt.validate(token).get("email");
			try {
				List<Questions> questions = games.getAllQuestions(author);
				if (questions != null) {
					json.put("status", "SUCCESS");
					json.put("questions", questions);
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

}
