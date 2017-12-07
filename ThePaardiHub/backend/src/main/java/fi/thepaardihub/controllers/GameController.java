package fi.thepaardihub.controllers;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import fi.thepaardihub.dao.games.GamesDao;
import fi.thepaardihub.dao.games.tables.Questions;
import fi.thepaardihub.dao.games.tables.Games;
import java.util.List;

@Service
@RestController
public class GameController {
	private GamesDao gamesdao;

	public GameController(GamesDao gamesdao) {
		this.gamesdao = gamesdao;
	}
	
	// Games
	
	public Games createGame(String author, String gameName, boolean isPrivate, String questions, String description) {
		try {
			Games add = new Games();
			add.setAuthor(author);
			add.setGameName(gameName);
			add.setPrivate(isPrivate);
			add.setDescription(description);
			add.setQuestions(questions);
			return gamesdao.saveOrUpdateAccount(add);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Games getGame(int id) {
		return gamesdao.getGame(id);
	}

	public Games getGame(int id, String author) {
		return gamesdao.getGame(id, author);
	}

	public List<Games> getAllGames() {
		return gamesdao.getAllGames();
	}

	public List<Games> getGameByAuthor(String author) {
		return gamesdao.getAllGamesByAuthor(author);
	}

	// Questions

	public Questions getQuestions(int id) {
		return gamesdao.getQuestion(id);
	}

	public Questions getQuestions(int id, String author) {
		return gamesdao.getQuestion(id, author);
	}

	public List<Questions> getAllQuestions() {
		return gamesdao.getAllQuestions();
	}

	public List<Questions> getAllQuestions(String author) {
		return gamesdao.getAllQuestionsByAuthor(author);
	}

	public Questions createQuestion(String author, String question, String correct,String wrong[],boolean privat) {
		try {
			Questions add = new Questions();
			add.setAuthor(author);
			add.setQuestion(question);
			add.setCorrect(correct);
			add.setFalse1(wrong[0]);
			add.setFalse2(wrong[1]);
			add.setFalse3(wrong[2]);
			add.setFalse4(wrong[3]);
			add.setFalse5(wrong[4]);
			add.setFalse6(wrong[5]);
			add.setFalse7(wrong[6]);
			add.setPrivate(privat);
			return gamesdao.saveOrUpdateAccount(add);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

}