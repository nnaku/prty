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
import fi.thepaardihub.security.Password;
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

    public Games createGame(String author, String gameName, boolean isPrivate, String questions) {
        try {
            Games add = new Games();
            add.setAuthor(author);
            add.setGameName(gameName);
          //  add.isPrivate(isPrivate); //En tiedä miten tää pitäs olla täälä
            add.setQuestions(questions);
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
}