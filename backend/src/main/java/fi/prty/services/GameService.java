package fi.prty.services;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import fi.prty.dao.games.GamesDao;
import fi.prty.dao.games.tables.Games;
import fi.prty.dao.games.tables.Questions;

import java.util.List;

@Service
@RestController

public class GameService {
    private GamesDao gamesdao;
  
    public GameService(GamesDao gamesdao) {
        this.gamesdao = gamesdao;
        sample();
    }

	// Games
	
	public Games createGame(String author, String gameName, boolean isPrivate, String questions, String description) throws Exception{
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
			return gamesdao.saveOrUpdateQuestion(add);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
	
	private void sample() {
		try {
			createQuestion("admin@prty.fi", "Which sorting algorithm has an upper bound of O(n lg n)?", "Merge Sort", new String[] {"Bubble Sort","Insertion Sort","Quicksort",null,null,null,null}, false);
			createQuestion("admin@prty.fi", "What is the worst case running time for finding an element in a Splay Tree?", "O(n)", new String[] {"O(n^2)","O(lg n)","O(n lg n)",null,null,null,null}, false);
			createQuestion("admin@prty.fi", "0xb - 0xe + 0xd?", "0xa", new String[] {"0xb","0xc","0xd",null,null,null,null}, false);
			createQuestion("admin@prty.fi", "Which operation takes more than O(1) time with the Hash Table data structure?", "Sort", new String[] {"Search","Inser","Delete",null,null,null,null}, false);
			createQuestion("admin@prty.fi", "What was Java called before it was Java?", "Oak", new String[] {"Maple","Snoo","C++ 2.0",null,null,null,null}, false);
			createGame("admin@prty.fi", "1st Game", false, "1;2;3;4;5", "Just a little test game");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}