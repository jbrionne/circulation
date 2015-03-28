package fr.cp.client.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.jboss.resteasy.annotations.Suspend;
import org.jboss.resteasy.spi.AsynchronousResponse;

@Path("/client")
public interface ClientResource {

	@GET
	@Path("getclient")
	@Consumes("application/json")
	void getClient(final @Suspend AsynchronousResponse response, @QueryParam(value = "id") Identifiant id);	

}