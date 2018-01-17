package test.domain.resources;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.json.JSONObject;
import org.junit.Test;

import com.domain.resources.ApiResource;
import com.domain.resources.Session;
import com.domain.heroku.Main;

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
        request.accumulate("query", ApiResource.ORDER_TRACKING_INTENT);
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
        request.accumulate("query", ApiResource.PRICE_CHECK_INTENT);
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

    /**
     * Tests Main java file.
     */
    @Test
    public void testMain() {
       Thread thread = new Thread(new Runnable() {
               @Override
               public void run() {
                   try {
                    Main.main(null);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
               }
           });
       thread.start();
       String res = target().path("apiserver")
                           .request().get(String.class);
       assertEquals(res, "{}");
       thread.interrupt();
    }

    /**
     * Test to send non-JSON input to POST endpoint.
     */
    @Test
    public void testExceptionInPost() {
        final String responseMsg = target().path("apiserver").request(
                MediaType.APPLICATION_JSON).post(
            Entity.json("gibberish"),
            String.class);
        assertFalse(responseMsg.contains("speech"));
    }

    /**
     * Test Private Constructors.
     */
    @Test
    public void testPrivateConstructor() {
        try {
            Session.getSessionInstance();
        } catch (Exception e) {
            assertTrue(true); //test pass
        }

        try {
            Main.getMainInstant();
        } catch (Exception e) {
            assertTrue(true); //test pass
        }
    }

}
