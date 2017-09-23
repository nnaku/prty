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
	
	public List<Games> getAllGames(){
		return (List<Games>) gamesRepo.findAll();
	}
	
	public List<Questions> getAllQuestions(){
		return (List<Questions>) quizRepo.findAll();
	}
	
	public void deleteGame(int id) {
		gamesRepo.delete(id);
	}
	
	public void deleteQuiz(int id) {
		quizRepo.delete(id);
	}

}
