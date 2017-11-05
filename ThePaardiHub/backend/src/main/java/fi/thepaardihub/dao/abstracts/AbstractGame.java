package fi.thepaardihub.dao.abstracts;

public abstract class AbstractGame {
	private String author;
	private String name;
	private String gameType;
	private String questions;
	private boolean isPrivate;
	
	public abstract String getAuthor();
	public abstract void setAuthor(String author);
	public abstract String getName();
	public abstract void setName(String name);
	public abstract String getGameType();
	public abstract void setGameType(String gameType);
	public abstract String getQuestions();
	public abstract void setQuestions(String questions);
	public abstract boolean isPrivate();
	public abstract void setPrivate(boolean isPrivate);
	
	

}
