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
    ArrayList<Question> Questions;
    boolean isprivate;  

    
     public String getGameName() {
         return gameName;
     }
    public String getAuthor() {
        return author;
    }

    public ArrayList<Question> getQuestions() {
        return Questions;
    }

    public boolean getPriv() {
        
      
        return true; // kysy matilta
    }
}