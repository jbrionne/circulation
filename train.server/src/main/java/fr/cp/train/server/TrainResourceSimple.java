package fr.cp.train.server;

import javax.inject.Inject;

import fr.cp.train.api.Train;
import fr.cp.train.api.TrainResource;

public class TrainResourceSimple implements TrainResource {

    @Inject
    private TrainProvider provider;
 
	@Override
	public Train getTrain(long id) {
		return provider.getTrain(id);
	}
}