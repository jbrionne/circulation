package fr.cp.translate.call;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import fr.cp.translate.api.Translate;
import fr.cp.translate.api.TranslateResource;



public class TestCallForTranslate {
	 
	  public static void main(String[] args) {
		  
		  ResteasyClient client = new ResteasyClientBuilder().build();
          ResteasyWebTarget target = client.target("http://localhost:9007/");

          TranslateResource simple = target.proxy(TranslateResource.class);
          Translate b1 = simple.getTranslate("test");
          
          System.out.println(b1.getComment());
          
          
	}
}
