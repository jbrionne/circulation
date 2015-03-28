package fr.cp.client.call;

import javax.ws.rs.client.InvocationCallback;
import javax.ws.rs.core.Response;


public interface ClientService {
	
	void getClient(InvocationCallback<Response> inv, long id);
	
}
