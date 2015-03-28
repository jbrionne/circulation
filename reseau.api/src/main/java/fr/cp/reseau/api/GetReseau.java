package fr.cp.reseau.api;

import java.io.Serializable;

public class GetReseau implements Serializable {

	private static final long serialVersionUID = 1L;

	private final long idTrain;
	private final long idRail;

	public GetReseau(long idTrain, long idRail) {
		super();
		this.idTrain = idTrain;
		this.idRail = idRail;
	}

	public long getIdTrain() {
		return idTrain;
	}

	public long getIdRail() {
		return idRail;
	}

}
