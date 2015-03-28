package fr.cp.train.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("/reseau")
public interface TrainResource {    
    
	@GET
	@Consumes("text/plain")
	@Produces("application/json")
    Train getTrain(@QueryParam(value = "id") long id);    
    
}