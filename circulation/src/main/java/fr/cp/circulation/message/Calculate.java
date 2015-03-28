package fr.cp.circulation.message;

import java.io.Serializable;

public class Calculate implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	private final long idBareme;
	private final String idReseau;
	private final long idClient;
	private final String comment;
	
	public Calculate(long idBareme, String idReseau, long idClient, String comment) {
		super();
		this.idBareme = idBareme;
		this.idReseau = idReseau;
		this.idClient = idClient;
		this.comment = comment;
		}	
	
	public long getIdBareme() {
		return idBareme;
	}
	public String getIdReseau() {
		return idReseau;
	}
	public long getIdClient() {
		return idClient;
	}

	public String getComment() {
		return comment;
	}
		
}

