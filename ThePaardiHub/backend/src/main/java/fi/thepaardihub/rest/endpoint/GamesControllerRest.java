/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.thepaardihub.rest.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import fi.thepaardihub.dao.games.tables.Games;
import fi.thepaardihub.controllers.GameController;
import fi.thepaardihub.rest.jsonobject.GameSet;

/**
 *
 * @author maaritemilia
 */
public class GamesControllerRest {
    
    private GameController games;
    
            public void setGames(GameController games){
                this.games = games;
                
            }
            	@PostMapping("/GameSet")
                public ResponseEntity<?> createGame(@RequestBody GameSet gameset) {
		
		Games game = games.createGame(gameset.getID(), gameset.getAuthor(), gameset.getPriv(),gameset.getQuestions());
		if(game == null) {
			return new ResponseEntity<Object>("No games created", new HttpHeaders(), HttpStatus.UNAUTHORIZED);
		}
		// TODO TOKEN HANDLING
		return new ResponseEntity<Object>("Games created", new HttpHeaders(), HttpStatus.OK);
	}

    
    
    
}
