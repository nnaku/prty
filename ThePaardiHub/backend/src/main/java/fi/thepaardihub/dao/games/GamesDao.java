package fi.thepaardihub.dao.games;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fi.thepaardihub.dao.games.repository.*;
import fi.thepaardihub.dao.games.tables.Games;
import fi.thepaardihub.dao.games.tables.Questions;



@Service
public class GamesDao {

	private GamesRepository gamesRepo;
	private QuestionsRepository quizRepo;

	@Autowired
	public GamesDao(GamesRepository gamesRepo, QuestionsRepository quizRepo) {
		super();
		this.gamesRepo = gamesRepo;
		this.quizRepo = quizRepo;
	}

	public List<Games> getAllGames() {
		return (List<Games>) gamesRepo.findAll();
	}

	public List<Games> getAllGamesByAuthor(String author) {
		return gamesRepo.findByAuthor(author);
	}
	
	public Games getGame(int id) {
		return gamesRepo.findOne(id);
	}
	
	public Games getGame(int id, String author) {
		return gamesRepo.findById(id, author);
	}
	
	public void deleteGame(int id) {
		gamesRepo.delete(id);
	}
	
	public Games saveOrUpdateAccount(Games add) {
		return gamesRepo.save(add);
	}
	
	
	// Questions
	
	public List<Questions> getAllQuestions() {
		return (List<Questions>) quizRepo.findAll();
	}
	
	public List<Questions> getAllQuestionsByAuthor(String author) {
		return quizRepo.findByAuthor(author);
	}

	public Questions getQuestion(int id) {
		return quizRepo.findOne(id);
	}
	
	public Questions getQuestion(int id, String author) {
		return quizRepo.findById(id, author);
	}

	public void deleteQuiz(int id) {
		quizRepo.delete(id);
	}



	public Questions saveOrUpdateAccount(Questions add) {
		return quizRepo.save(add);
	}





}
