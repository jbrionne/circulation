package fr.cp.translate.call;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import fr.cp.translate.api.Translate;
import fr.cp.translate.api.TranslateResource;



public class CallForTranslate implements TranslateService {
	
	private TranslateResource simple;
	
	public CallForTranslate() {
		 ResteasyClient client = new ResteasyClientBuilder().build();
         ResteasyWebTarget target = client.target("http://localhost:9007/");
         simple = target.proxy(TranslateResource.class);
    }

	@Override
	public Translate getTranslate(String comment) {
		return simple.getTranslate(comment);	
	}
	
}
