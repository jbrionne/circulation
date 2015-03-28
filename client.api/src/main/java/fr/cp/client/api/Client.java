package fr.cp.client.api;

import java.io.Serializable;

public class Client implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private long id;
	private String firstname;
	private String lastname;	
	
	public Client(){		
	}
	
	public Client(long id, String firstname, String lastname) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
	}
	
	public String getFirstname() {
		return firstname;
	}
	public String getLastname() {
		return lastname;
	}

	public long getId() {
		return id;
	}
	
}
