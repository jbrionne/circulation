package fr.cp.circulation.message;

import java.io.Serializable;

public class GetTranslate implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final String comment;
	private final long id;
	
	public GetTranslate(long id, String comment) {
		this.comment = comment;
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public long getId() {
		return id;
	}
}
