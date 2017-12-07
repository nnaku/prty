/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.thepaardihub.rest.jsonobject;

import java.util.ArrayList;

/**
 *
 * @author maaritemilia
 */
public class GameSet {

    String author;
    String gameName;
    ArrayList<Question> questions;
    boolean isprivate;
    String description;

    public String getGameName() {
        return gameName;
    }

    public String getAuthor() {
        return author;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public boolean getPriv() {
        return true;
    }
     public String getDescription() {
        return description;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public void setIsprivate(boolean isprivate) {
        this.isprivate = isprivate;
    }
    public void setDescription(String Description) {
        
    }
}
