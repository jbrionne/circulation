package fr.cp.client.call;

import javax.ws.rs.client.InvocationCallback;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;



public class CallForClient implements ClientService {
	 
	private ResteasyWebTarget target;
	
	public CallForClient() {
		  ResteasyClient client = new ResteasyClientBuilder().build();
          target = client.target("http://localhost:9004/");
    }

	@Override
	public void getClient(InvocationCallback<Response> inv, long id) {		
		target.path("client").path("getclient").queryParam("id", id).request().async()
					.get(inv);				
	}
	  
}
