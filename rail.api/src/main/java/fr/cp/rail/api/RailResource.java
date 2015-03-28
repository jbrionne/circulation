package fr.cp.rail.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("/reseau")
public interface RailResource {    
    
	@GET
	@Consumes("text/plain")
	@Produces("application/json")
    Rail getRail(@QueryParam(value = "id") long id);    
    
}