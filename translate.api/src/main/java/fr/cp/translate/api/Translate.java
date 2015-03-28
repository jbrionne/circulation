package fr.cp.translate.api;

import java.io.Serializable;

public class Translate implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private long id;
	private String comment;
	
	
	public Translate(){		
	}

	public Translate(String comment) {
		super();
		this.comment = comment;
	}


	public String getComment() {
		return comment;
	}

	public long getId() {
		return id;
	}

	public void setId(long getTranslateId) {
		this.id = getTranslateId;
	}
	
}
