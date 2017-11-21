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

    private GamesDao games;
  

    public GameController(GamesDao games) {
        this.games = games;
    }

    @RequestMapping("/games")
    public @ResponseBody
    String getGames() {
        return "{\"message\":\"this returns user info\"}";}

    public Games createGame(String author, String gameName, boolean isPrivate, ArrayList<Question> questions ) {
        try {
            Games add = new Games();
            add.setAuthor(author);
            add.setGameName(gameName);
            add.setPrivate(isPrivate);
            add.setQuestions(createQuestions(questions));
            return add;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    
    public List<Games> getallGames() {
        return games.getAllGames();
    }

    public List<Questions> getallQuestions() {
        return games.getAllQuestions();
    }
    public Games getGame() {
        try {
			Games game = (Games) games.getAllGames();

			if (game != null) {
				return game;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
    }

    public String createQuestions(ArrayList<Question> questions) {
        //foreachin sisällä 
        String retVal = "";
        for(Question e: questions) {
            Questions add = new Questions();
            add.setAuthor(e.getAuthor());
            add.setCorrect(e.getAnwser());
            add.setFalse1(e.getFalse1());
            add.setFalse2(e.getFalse2());
            add.setFalse3(e.getFalse3());
            add.setFalse4(e.getFalse4());
            add.setFalse5(e.getFalse5());
            add.setFalse6(e.getFalse6());
            add.setFalse7(e.getFalse7());
            games.saveOrUpdateAccount(add);
            retVal += add.getId() + ";";
        }
     return retVal;
            
    }
}