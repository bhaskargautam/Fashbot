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
	
	public static final String OrderTrackingIntent = "Order tracking";
	public static final String OrderTrackingDefault = "Your order is in transit, will arrive by %s";
	public static final String PriceCheckIntent = "Price Check";
	public static final String PriceCheckDefault = "It is just %s euros, including your 10%% discount";
	
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
    		JSONObject request = new JSONObject(req);
    		String sessionId = (String) request.get("sessionId");
    		Session.addSession(sessionId);
    		
    		JSONObject response = new JSONObject();
    		if(req.contains(OrderTrackingIntent)) {
    			response.accumulate("speech", 
    					String.format(OrderTrackingDefault, Session.getArrivalDate(sessionId)));
        		
    		} else if (req.contains(PriceCheckIntent)) {
    			response.accumulate("speech", 
    					String.format(PriceCheckDefault, Session.getOrderPrice(sessionId)));
    		}
    		else if(req.contains("kab")) {
    			response.accumulate("speech", "Aa jayega order don't worry");
    		}
    		 		
        return Response.status(200).entity(response.toString()).build();
    }
}               
