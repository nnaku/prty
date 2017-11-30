package fi.thepaardihub.dao.games.tables;

import fi.thepaardihub.dao.abstracts.AbstractGame;
import javax.persistence.*;

@Entity
@Table(name="Games")
public class Games extends AbstractGame{
		
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String author;
	
	private String gameName;
	
	@Column(name="private")
	private boolean isPrivate = true;
	
	private String questions;

	public Games() {}
	public Games(String author, String gameName, boolean isPrivate, String questions) {
		super();
		this.author = author;
		this.gameName = gameName;
		this.isPrivate = isPrivate;
		this.questions = questions;
	}
	public int getId() {
		return id;
	}
	
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getGameName() {
		return gameName;
	}
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	public boolean isPrivate() {
		return isPrivate;
	}
	public void setPrivate(boolean isPrivate) {
		this.isPrivate = isPrivate;
	}
	public String getQuestions() {
		return questions;
	}
	public void setQuestions(String questions) {
		this.questions = questions;
	}

    @Override
    public String getName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getGameType() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setGameType(String gameType) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
	
	
}
