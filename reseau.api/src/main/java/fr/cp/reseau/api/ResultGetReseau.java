package fr.cp.reseau.api;

import java.io.Serializable;

public class ResultGetReseau implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String score;	

	public ResultGetReseau(String id, String score) {
		super();
		this.setId(id);
		this.score = score;
	}
	

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}
	
	
}
