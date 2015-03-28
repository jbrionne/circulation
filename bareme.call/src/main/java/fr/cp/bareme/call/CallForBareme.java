package fr.cp.bareme.call;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import fr.cp.bareme.api.Bareme;
import fr.cp.bareme.api.BaremeResource;



public class CallForBareme implements BaremeService {
	
	private BaremeResource simple;
	
	public CallForBareme() {
		 ResteasyClient client = new ResteasyClientBuilder().build();
         ResteasyWebTarget target = client.target("http://localhost:9003/");
         simple = target.proxy(BaremeResource.class);
    }

	@Override
	public Bareme getBareme(long id) {
		return simple.getBareme(id);	
	}
	
}
