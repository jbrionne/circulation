package fr.cp.circulation.message;

import java.io.Serializable;

public class ResultCalculate implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String score;	

	public ResultCalculate(String score) {
		super();
		this.score = score;
	}
	

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}
	
	
}
