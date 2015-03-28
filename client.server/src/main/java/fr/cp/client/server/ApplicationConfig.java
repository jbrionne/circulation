package fr.cp.client.server;

import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("min")
public class ApplicationConfig extends Application {

	@Override
	public Set<Class<?>> getClasses() {	 	 
		Set<Class<?>> resources = new java.util.HashSet<Class<?>>();
		resources.add(ClientResourceSimple.class);
		return resources;
	}

}