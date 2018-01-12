package com.domain.resources;

import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

@Path("apiserver")
public class ApiResource {
	
	private static final Logger LOGGER = Logger.getLogger(ApiResource.class.getName() );
	
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getIt() {
    	System.out.println("Hola esto es un get");
        return Response.status(200).entity("{}").build();
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response postIt(String req) {
    		LOGGER.info("Received Request: " + req);
    		
    		JSONObject obj = new JSONObject();
    		obj.accumulate("fulfillmentText", "Aa jayega order don't worry");
    		obj.accumulate("speech", "Aa jayega order don't worry");
    		
        return Response.status(200).entity(obj.toString()).build();
    }
}               
