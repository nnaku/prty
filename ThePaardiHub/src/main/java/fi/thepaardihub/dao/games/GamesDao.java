package fi.thepaardihub.dao.games;

import org.springframework.beans.factory.annotation.Autowired;

import fi.thepaardihub.dao.games.repository.*;

public class GamesDao {
	
	private GamesRepository gamesRepo;
	private QuestionsRepository quizRepo;
	
	@Autowired
	public GamesDao(GamesRepository gamesRepo, QuestionsRepository quizRepo) {
		super();
		this.gamesRepo = gamesRepo;
		this.quizRepo = quizRepo;
	}
	
	

}
