package fr.cp.train.api;

import java.io.Serializable;

public class Train implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long id;		
	private String name;
	
	public Train(){		
	}	
	
	public Train(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}	
}
