package fi.thepaardihub.dao.games.tables;

import javax.persistence.*;

@Entity
@Table(name="Games")
public class Games {
		
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String author;
	
	private String gameName;
	
	@Column(name="private")
	private boolean isPrivate = true;
	
	private String questions;
    
	private String description;

	public Games() {
        }
	public Games(String author, String gameName, boolean isPrivate, String questions, String description) {
		super();
		this.author = author;
		this.gameName = gameName;
		this.isPrivate = isPrivate;
		this.questions = questions;
                this.description = description;
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

    public String getDescription() {
        return description;
    }

 
    public void setDescription(String description) {
        this.description = description;
    }

}
