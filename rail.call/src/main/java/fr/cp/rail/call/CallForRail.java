package fr.cp.rail.call;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import fr.cp.rail.api.Rail;
import fr.cp.rail.api.RailResource;



public class CallForRail implements RailService {
	
	private RailResource simple;
	
	public CallForRail() {
		  ResteasyClient client = new ResteasyClientBuilder().build();
          ResteasyWebTarget target = client.target("http://localhost:9005/");
          simple = target.proxy(RailResource.class);
    }

	@Override
	public Rail getRail(long id) {
		return simple.getRail(id);	
	}	 
	
}
