package fr.cp.client.server;

import java.io.IOException;

import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.jboss.resteasy.spi.AsynchronousResponse;

import fr.cp.client.api.Client;
import fr.cp.client.api.ClientResource;
import fr.cp.client.api.Identifiant;

public class ClientResourceSimple implements ClientResource {

	@Inject
	private ClientProvider provider;

	@Override
	public void getClient(final AsynchronousResponse response, final Identifiant id) {
		Thread t = new Thread() {
			@Override
			public void run() {
				try {

					Client client = provider.getClient(Long.valueOf(id.getId()));
					
					ObjectMapper mapper = new ObjectMapper();
					String json = null;					
					try {
						// display to console
						json = mapper.writeValueAsString(client);
					} catch (JsonGenerationException e) {
						e.printStackTrace();
					} catch (JsonMappingException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}

					Response jaxrs = Response.ok(json,
							MediaType.APPLICATION_JSON).build();
					response.setResponse(jaxrs);					
				        
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		t.start();

	}

}