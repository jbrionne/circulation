package fr.cp.reseau.message;

import java.io.Serializable;

public class GetTrain implements Serializable {

	private static final long serialVersionUID = 1L;
	

	private final long id;

	public GetTrain(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}
}
