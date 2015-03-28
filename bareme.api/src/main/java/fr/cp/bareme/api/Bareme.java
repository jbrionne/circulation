package fr.cp.bareme.api;

import java.io.Serializable;

public class Bareme implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	private long id;	
	private long cost;
	
	
	public Bareme(){		
	}	
	
	public long getId() {
		return id;
	}

	public Bareme(long id, long cost) {
		super();
		this.id = id;
		this.cost = cost;
	}


	public long getCost() {
		return cost;
	}
	
}
