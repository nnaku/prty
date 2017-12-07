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
    
    public Games getGame(int id) {
		return gamesdao.getGame(id);
    }
    
    public Questions getQuestions(int id) {
    	return gamesdao.getQuestion(id);
    }

    public String createQuestions(List<Question> questions) {
        //foreachin sisällä 
        String retVal = "";

        for(Question e: questions) {
            Questions add = new Questions();
            add.setAuthor(e.getAuthor());
            add.setQuestion(e.getQuestion());
            add.setCorrect(e.getAnwser());
            add.setFalse1(e.getFalse1());
            add.setFalse2(e.getFalse2());
            add.setFalse3(e.getFalse3());
            add.setFalse4(e.getFalse4());
            add.setFalse5(e.getFalse5());
            add.setFalse6(e.getFalse6());
            add.setFalse7(e.getFalse7());
            add.setPrivate(e.isPrivate());
            gamesdao.saveOrUpdateAccount(add);
            retVal += add.getId() + ";";
        }
     return retVal;
            
    }
}