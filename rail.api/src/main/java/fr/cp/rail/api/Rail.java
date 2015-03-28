package fr.cp.rail.api;

import java.io.Serializable;

public class Rail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long id;		
	private String name;
	
	public Rail(){		
	}	
	
	public Rail(long id, String name) {
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
