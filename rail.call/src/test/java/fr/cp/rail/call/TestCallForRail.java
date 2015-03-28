package fr.cp.rail.call;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import fr.cp.rail.api.Rail;
import fr.cp.rail.api.RailResource;



public class TestCallForRail {
	 
	  public static void main(String[] args) {
		  
		  ResteasyClient client = new ResteasyClientBuilder().build();
          ResteasyWebTarget target = client.target("http://localhost:9005/");

          RailResource simple = target.proxy(RailResource.class);
          Rail r1 = simple.getRail(2);
          
          System.out.println(r1.getName());
          
          
	}
}
