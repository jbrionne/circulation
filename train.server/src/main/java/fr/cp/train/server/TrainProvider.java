package fr.cp.train.server;

import javax.inject.Singleton;

import fr.cp.train.api.Train;

@Singleton
public class TrainProvider {

	public Train getTrain(long id){
		if(id == 1){
			return new Train(1, "train 1");
		} else if (id == 2){
			return new Train(2, "train 2");
		} else {
			throw new IllegalArgumentException("No train for id " + id);
		}		
	}
}
