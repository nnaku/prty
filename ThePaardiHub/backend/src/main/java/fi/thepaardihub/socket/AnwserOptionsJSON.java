package fi.thepaardihub.socket;

import java.util.ArrayList;

public class AnwserOptionsJSON {
	
	private String option1;
	private String option2;
	private String option3;
	private String option4;
	private boolean playGame;
	public AnwserOptionsJSON(ArrayList<String> options, boolean playGame) {
		option1 = options.get(0);
		option2 = options.get(1);
		option3 = options.get(2);
		option4 = options.get(3);
		this.playGame = playGame;
	}
	public String getOption1() {
		return option1;
	}
	public String getOption2() {
		return option2;
	}
	public String getOption3() {
		return option3;
	}
	public String getOption4() {
		return option4;
	}
	
	public boolean getPlayGame() {
		return playGame;
		
	}
	
	public ArrayList<String> getOptions(){
		ArrayList<String> options = new ArrayList<>();
		options.add(option1);
		options.add(option2);
		options.add(option3);
		options.add(option4);
		return options;
	}
	

}
