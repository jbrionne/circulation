package fr.cp.train.call;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import fr.cp.train.api.Train;
import fr.cp.train.api.TrainResource;



public class TestCallForTrain {
	 
	  public static void main(String[] args) {
		  
		  ResteasyClient client = new ResteasyClientBuilder().build();
          ResteasyWebTarget target = client.target("http://localhost:9006/");

          TrainResource simple = target.proxy(TrainResource.class);
          Train r1 = simple.getTrain(2);
          
          System.out.println(r1.getName());
          
          
	}
}
