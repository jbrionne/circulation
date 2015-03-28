package fr.cp.bareme.call;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import fr.cp.bareme.api.Bareme;
import fr.cp.bareme.api.BaremeResource;



public class TestCallForBareme {
	 
	  public static void main(String[] args) {
		  
		  ResteasyClient client = new ResteasyClientBuilder().build();
          ResteasyWebTarget target = client.target("http://localhost:9003/");

          BaremeResource simple = target.proxy(BaremeResource.class);
          Bareme b1 = simple.getBareme(1);
          
          System.out.println(b1.getCost());
          
          
	}
}
