package com.domain.resources;

import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

/**
 * The endpoint configured with dialog flow.
 * @author bhaskargautam
 *
 */
@Path("apiserver")
public class ApiResource {

    /**
     * BASIC LOGGER FOR the class.
     */
    private static final Logger LOGGER = Logger.getLogger(
                ApiResource.class.getName());

    /**
     * INTENT name as defined in dialog flow.
     */
    public static final String ORDER_TRACKING_INTENT = "Order tracking";

    /**
     * Default response for an Order tracking intent.
     */
    public static final String ORDER_TRACKING_DEFAULT =
            "Your order is in transit, will arrive by %s";

    /**
     * INTENT name as defined in dialog flow.
     */
    public static final String PRICE_CHECK_INTENT = "Price Check";

    /**
     * Default response of price check intent.
     */
    public static final String PRICE_CHECK_DEFAULT =
            "It is just %s euros, including your 10%% discount";

    /**
     * GET endpoint of the server used to test if service is up.
     * @return 200 OK
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getIt() {
        System.out.println("Hola esto es un get");
        return Response.status(Response.Status.OK).entity("{}").build();
    }

    /**
     * POST endpoint configured with dialow flow.
     * All messages sent to chatbot flow through this endpoint.
     * @param req JSON string containing the query and intent.
     * @return JSON with tag speech.
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response postIt(final String req) {
        LOGGER.info("Received Request: " + req);
        JSONObject response = new JSONObject();
        try {
            JSONObject request = new JSONObject(req);
            String sessionId = (String) request.get("sessionId");
            LOGGER.info("Session ID: " + sessionId);
            Session.addSession(sessionId);

            if (req.contains(ORDER_TRACKING_INTENT)) {
                response.accumulate("speech",
                        String.format(ORDER_TRACKING_DEFAULT,
                        Session.getArrivalDate(sessionId)));
            } else if (req.contains(PRICE_CHECK_INTENT)) {
                response.accumulate("speech",
                    String.format(PRICE_CHECK_DEFAULT,
                    Session.getOrderPrice(sessionId)));
            } else if (req.contains("kab")) {
                response.accumulate("speech",
                        "Aa jayega order don't worry");
            }
        } catch (Exception e) {
            LOGGER.warning("Failed to get response" + e.getMessage());
        }

        LOGGER.info("Response: " + response.toString());
        return Response.status(Response.Status.OK)
                .entity(response.toString()).build();
    }
}
