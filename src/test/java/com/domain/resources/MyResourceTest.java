package com.domain.resources;

import javax.ws.rs.core.Application;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import com.domain.resources.*;

public class MyResourceTest extends JerseyTest {

    @Override
    protected Application configure() {
        return new ResourceConfig(ApiResource.class);
    }

    /**
     * Test to see that the message "Got it!" is sent in the response.
     */
    @Test
    public void testGetIt() {
        final String responseMsg = target().path("apiserver").request().get(String.class);
        assertTrue(responseMsg.contains("speech"));
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
    
}
