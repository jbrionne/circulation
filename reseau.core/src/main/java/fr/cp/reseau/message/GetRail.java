package fr.cp.reseau.message;

import java.io.Serializable;

public class GetRail implements Serializable {

	private static final long serialVersionUID = 1L;
	

	private final long id;

	public GetRail(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}
}
