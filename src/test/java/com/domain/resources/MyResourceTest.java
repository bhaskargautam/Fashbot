package com.domain.resources;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.json.JSONObject;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Basic Unit test cases for Fashbot.
 * @author bhaskargautam
 *
 */
public class MyResourceTest extends JerseyTest {

    @Override
    protected final Application configure() {
        return new ResourceConfig(ApiResource.class);
    }

    /**
     * Test to see that the message "Got it!" is sent in the response.
     */
    @Test
    public void testGetIt() {
        final String responseMsg = target().path("apiserver").
                request().get(String.class);
        assertEquals("{}", responseMsg);
    }

    /**
     * Test Session Creation and access data from it.
     */
    @Test
    public void testGetSession() {
        Session.addSession("123");
        assertFalse(Session.getArrivalDate("123").isEmpty());
        assertFalse(Session.getOrderPrice("123").isEmpty());
    }

    /**
     * Tests Order Tracking Intent.
     */
    @Test
    public void testOrderTracking() {
        JSONObject request = new JSONObject();
        request.accumulate("query", ApiResource.OrderTrackingIntent);
        request.accumulate("sessionId", "123");

        final String responseMsg = target().path("apiserver").request(
                MediaType.APPLICATION_JSON).post(
                Entity.json(request.toString()),
                String.class);
        assertTrue(responseMsg.contains("speech"));
    }

    /**
     * Tests Order Price Check.
     */
    @Test
    public void testOrderPriceCheck() {
        JSONObject request = new JSONObject();
        request.accumulate("query", ApiResource.PriceCheckIntent);
        request.accumulate("sessionId", "123");

            final String responseMsg = target().path("apiserver").request(
                    MediaType.APPLICATION_JSON).post(
                Entity.json(request.toString()),
                String.class);
            assertTrue(responseMsg.contains("speech"));
    }

    /**
     * Tests Default message.
     */
    @Test
    public void testDefault() {
        JSONObject request = new JSONObject();
        request.accumulate("query", "kab");
        request.accumulate("sessionId", "123");

        final String responseMsg = target().path("apiserver").request(
                MediaType.APPLICATION_JSON).post(
            Entity.json(request.toString()),
            String.class);
        assertTrue(responseMsg.contains("speech"));
    }
}
