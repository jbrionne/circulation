package fr.cp.translate.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("/bareme")
public interface TranslateResource {    
    
	@GET
	@Consumes("text/plain")
	@Produces("application/json")
    Translate getTranslate(@QueryParam(value = "comment") String comment);    
    
}