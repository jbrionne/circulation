package fr.cp.client.server;

import javax.inject.Singleton;

import fr.cp.client.api.Client;

@Singleton
public class ClientProvider {

	public Client getClient(long id){
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(id == 1){
			return new Client(1, "c 1", "client 1");
		} else if (id == 2){
			return new Client(2, "c 2", "client 2");
		} else {
			throw new IllegalArgumentException("No client for id " + id);
		}		
	}
}
