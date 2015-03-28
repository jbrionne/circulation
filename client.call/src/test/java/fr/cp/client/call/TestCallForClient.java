package fr.cp.client.call;

import javax.ws.rs.client.InvocationCallback;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import fr.cp.client.api.Client;

public class TestCallForClient {	

	public static void main(String[] args) throws Exception {

		ResteasyClient client = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = client.target("http://localhost:9004/");		

		{
			target.path("client").path("getclient").queryParam("id", 2).request().async()
					.get(new InvocationCallback<Response>() {
						@Override
						public void completed(Response response) {
							infoResponse(response);
						}

						@Override
						public void failed(Throwable throwable) {
							System.out.println("Invocation failed.");
							throwable.printStackTrace();
						}
					});

		}
		System.out.println("do something else");

	}

	private static void infoResponse(final Response response) {
		System.out.println("Response status code " + response.getStatus()
				+ " received.");
		Client c1 = null;
		try {
			c1 = response.readEntity(Client.class);
		} catch (Exception e) {
			System.out.println(e);
		}
		System.out.println(c1.getFirstname());
	}

}
