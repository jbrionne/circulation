package fr.cp.train.call;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import fr.cp.train.api.Train;
import fr.cp.train.api.TrainResource;



public class CallForTrain implements TrainService {
	
	private TrainResource simple;
	
	public CallForTrain() {
		  ResteasyClient client = new ResteasyClientBuilder().build();
          ResteasyWebTarget target = client.target("http://localhost:9006/");
          simple = target.proxy(TrainResource.class);
    }

	@Override
	public Train getTrain(long id) {
		return simple.getTrain(id);	
	}	 
	
}
