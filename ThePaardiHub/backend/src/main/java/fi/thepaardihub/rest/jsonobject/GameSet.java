/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.thepaardihub.rest.jsonobject;

/**
 *
 * @author maaritemilia
 */
public class GameSet {
    String ID;
    String author;
    String Questions;
    boolean isprivate;  
    
    public String getID() {
        return ID;
    }

    public String getAuthor() {
        return author;
    }

    public String getQuestions() {
        return Questions;
    }

    public boolean getPriv() {
        return true; // kysy matilta
    }
}
