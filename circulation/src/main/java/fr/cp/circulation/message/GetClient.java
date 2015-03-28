package fr.cp.circulation.message;

import java.io.Serializable;

public class GetClient implements Serializable {
	
	private static final long serialVersionUID = 1L;
	

	private final long id;

	public GetClient(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}
}
