package fi.thepaardihub.dao.games.tables;

import javax.persistence.*;

@Entity

@Table(name="Questions")
public class Questions {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String author;
	@Column(name="Private")
	private boolean isPrivate = true;
	private String question;
	private String correct;

	private String false1;
	private String false2;
	private String false3;
	private String false4;
	private String false5;
	private String false6;
	private String false7;
	
	public Questions() {}
	public Questions(String author, boolean isPrivate,String question, String correct, String false1, String false2, String false3,
			String false4, String false5, String false6, String false7) {
		super();
		this.author = author;
		this.isPrivate = isPrivate;
		this.question = question;
		this.correct = correct;
		this.false1 = false1;
		this.false2 = false2;
		this.false3 = false3;
		this.false4 = false4;
		this.false5 = false5;
		this.false6 = false6;
		this.false7 = false7;
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
	public boolean isPrivate() {
		return isPrivate;
	}
	public void setPrivate(boolean isPrivate) {
		this.isPrivate = isPrivate;
	}
	
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getQuestion() {
		return this.question;
	}
	public String getCorrect() {
		return correct;
	}
	public void setCorrect(String correct) {
		this.correct = correct;
	}
	public String getFalse1() {
		return false1;
	}
	public void setFalse1(String false1) {
		this.false1 = false1;
	}
	public String getFalse2() {
		return false2;
	}
	public void setFalse2(String false2) {
		this.false2 = false2;
	}
	public String getFalse3() {
		return false3;
	}
	public void setFalse3(String false3) {
		this.false3 = false3;
	}
	public String getFalse4() {
		return false4;
	}
	public void setFalse4(String false4) {
		this.false4 = false4;
	}
	public String getFalse5() {
		return false5;
	}
	public void setFalse5(String false5) {
		this.false5 = false5;
	}
	public String getFalse6() {
		return false6;
	}
	public void setFalse6(String false6) {
		this.false6 = false6;
	}
	public String getFalse7() {
		return false7;
	}
	public void setFalse7(String false7) {
		this.false7 = false7;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	
	
}
