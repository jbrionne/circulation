package fr.cp.bareme.server;

import javax.inject.Singleton;

import fr.cp.bareme.api.Bareme;

@Singleton
public class BaremeProvider {

	public Bareme getBareme(long id){
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(id == 1){
			return new Bareme(1, 120);
		} else if (id == 2){
			return new Bareme(2, 250);
		} else {
			throw new IllegalArgumentException("No bareme for id " + id);
		}		
	}
}
