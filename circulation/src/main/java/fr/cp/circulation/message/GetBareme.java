package fr.cp.circulation.message;

import java.io.Serializable;

public class GetBareme implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private final long id;

	public GetBareme(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

}
