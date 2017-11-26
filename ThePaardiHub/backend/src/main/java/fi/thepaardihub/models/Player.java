package fi.thepaardihub.models;

public class Player {

	private String name;
	private int score;
	private String anwser;

	public Player() {
		name = "unknown";
		score = 0;
		anwser = "";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getAnwser() {
		return anwser;
	}

	public void setAnwser(String anwser) {
		this.anwser = anwser;
	}

}
