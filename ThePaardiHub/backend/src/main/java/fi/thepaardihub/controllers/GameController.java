package fi.thepaardihub.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import fi.thepaardihub.dao.games.GamesDao;
import fi.thepaardihub.dao.users.UsersDao;
import fi.thepaardihub.dao.games.tables.Questions;
import fi.thepaardihub.dao.users.tables.UserAccounts;
import fi.thepaardihub.dao.users.tables.UserRoles;
import fi.thepaardihub.security.Password;

public class GameController {

    private GamesDao games;
    private Questions questions;

    public GameController(GamesDao games) {
        this.games = games;
    }

    public Questions getQuestions() {
        return questions;
    }

    public void setQuestions(Questions questions) {
        this.questions = questions;
    }
}