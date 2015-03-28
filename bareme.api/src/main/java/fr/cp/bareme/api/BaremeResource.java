package fr.cp.bareme.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("/bareme")
public interface BaremeResource {    
    
	@GET
	@Consumes("text/plain")
	@Produces("application/json")
    Bareme getBareme(@QueryParam(value = "id") long id);    
    
}