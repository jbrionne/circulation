package fr.cp.reseau.api;

import java.io.Serializable;

public class Reseau implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long id;		
	private String name;
	
	public Reseau(){		
	}	
	
	public Reseau(long id, String name) {
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
