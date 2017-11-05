package fi.thepaardihub.dao.abstracts;

public abstract class AbstractQuestions {

	private String author;
	private String question;
	private boolean isPrivate;
	private String correct;
	
	public abstract String getAuthor();
	public abstract void setAuthor(String author);
	public abstract String getQuestion();
	public abstract void setQuestion(String question);
	public abstract boolean isPrivate();
	public abstract void setPrivate(boolean isPrivate);
	public abstract String getCorrect();
	public abstract void setCorrect(String correct);
	
	
	

}
