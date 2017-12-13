package fi.thepaardihub.models;

public class Player {

	private String name;
	private int score;
	private String answer;
	private int anwserTime = 0;
	private int addedPoints = 0;

	public Player() {
		name = "unknown";
		score = 0;
		answer = "";
	}
	public Player(String name) {
		this.name = name;
		score = 0;
		answer = "";
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

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public int getAnwserTime() {
		return anwserTime;
	}
	public void setAnwserTime(int anwserTime) {
		this.anwserTime = anwserTime;
	}
	public int getAddedPoints() {
		return addedPoints;
	}
	public void setAddedPoints(int addedPoints) {
		this.addedPoints = addedPoints;
	}
	

}
