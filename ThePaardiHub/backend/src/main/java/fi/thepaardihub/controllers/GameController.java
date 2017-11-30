package fi.thepaardihub.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import fi.thepaardihub.dao.games.GamesDao;
import fi.thepaardihub.dao.users.UsersDao;
import fi.thepaardihub.dao.games.tables.Questions;
import fi.thepaardihub.dao.games.tables.Games;
import fi.thepaardihub.rest.jsonobject.Question;
import fi.thepaardihub.security.Password;
import java.util.ArrayList;
import java.util.List;


@Service
@RestController
public class GameController {

    private GamesDao gamesdao;
  

    public GameController(GamesDao gamesdao) {
        this.gamesdao = gamesdao;
    }

    @RequestMapping("/games")
    public @ResponseBody
    String getGames() {
        return "{\"message\":\"this returns user info\"}";}

    public Games createGame(String author, String gameName, boolean isPrivate, List<Question> questions ) {
        try {
            Games add = new Games();
            add.setAuthor(author);
            add.setGameName(gameName);
            add.setPrivate(isPrivate);
            add.setQuestions(createQuestions(questions));
            gamesdao.saveOrUpdateAccount(add);
            return add;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    
    public List<Games> getallGames() {
        return gamesdao.getAllGames();
    }

    public List<Questions> getallQuestions() {
        return gamesdao.getAllQuestions();
    }
    public Games getGame() {
        try {
			Games game = (Games) gamesdao.getAllGames();

			if (game != null) {
				return game;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
    }

    public String createQuestions(List<Question> questions) {
        //foreachin sisällä 
        String retVal = "";
        for(Question q: questions) {
            Questions add = new Questions();
            add.setAuthor(q.getAuthor());
            add.setCorrect(q.getAnwser());
            add.setQuestion(q.getQuestion());
            add.setFalse1(q.getFalse1());
            add.setFalse2(q.getFalse2());
            add.setFalse3(q.getFalse3());
            add.setFalse4(q.getFalse4());
            add.setFalse5(q.getFalse5());
            add.setFalse6(q.getFalse6());
            add.setFalse7(q.getFalse7());
            gamesdao.saveOrUpdateAccount(add);
            retVal += add.getId() + ";";
        }
     return retVal;
            
    }
}