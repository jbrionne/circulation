package fr.cp.rail.server;

import javax.inject.Singleton;

import fr.cp.rail.api.Rail;

@Singleton
public class RailProvider {

	public Rail getRail(long id){
		if(id == 1){
			return new Rail(1, "rail 1");
		} else if (id == 2){
			return new Rail(2, "rail 2");
		} else {
			throw new IllegalArgumentException("No rail for id " + id);
		}		
	}
}
