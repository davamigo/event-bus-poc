package com.privalia.poc.eventbus.user.event;

import com.privalia.poc.eventbus.user.core.EventPayload;
import com.privalia.poc.eventbus.user.core.EventPayloadHashMap;
import com.privalia.poc.eventbus.user.core.RawEvent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Unit test for event class UserLoggedIn
 *
 * @author david.amigo
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserLoggedInTest {

    /**
     * Test static bindingName()
     */
    @Test
    public void testBindingName() {
        assertEquals("UserLoggedIn", UserLoggedIn.bindingName());
    }

    /**
     * Test the basic constructor
     */
    @Test
    public void testBasicConstructor() {

        UserLoggedIn event = new UserLoggedIn(100, "101", "102", "103");

        assertEquals(100, event.id());
        assertEquals("101", event.firstName());
        assertEquals("102", event.lastName());
        assertEquals("103", event.address());

        assertEquals("UserLoggedIn", event.eventName());

        EventPayload payload = event.eventPayload();
        assertEquals("100", payload.get("id"));
        assertEquals("101", payload.get("first_name"));
        assertEquals("102", payload.get("last_name"));
        assertEquals("103", payload.get("address"));
    }

    /**
     * Test the basic constructor with null values
     */
    @Test
    public void testBasicConstructorWithNullValues() {

        UserLoggedIn event = new UserLoggedIn(0L, null, null, null);

        assertEquals(0L, event.id());
        assertNull(event.firstName());
        assertNull(event.lastName());
        assertNull(event.address());

        EventPayload payload = event.eventPayload();
        assertEquals(null, payload.get("id"));
        assertEquals(null, payload.get("first_name"));
        assertEquals(null, payload.get("last_name"));
        assertEquals(null, payload.get("address"));
    }

    /**
     * Test the copy constructor
     */
    @Test
    public void testCopyConstructor() {

        Date timestamp = new Date();

        EventPayload payload = new EventPayloadHashMap();
        payload.put("id", "200");
        payload.put("first_name", "201");
        payload.put("last_name", "202");
        payload.put("address", "203");

        RawEvent sourceEvent = new RawEvent("UserLoggedIn", timestamp, payload);
        UserLoggedIn event = new UserLoggedIn(sourceEvent);

        assertEquals(200, event.id());
        assertEquals("201", event.firstName());
        assertEquals("202", event.lastName());
        assertEquals("203", event.address());

        assertEquals(timestamp.getTime(), event.eventTimestamp().getTime());
    }

    /**
     * Test the copy constructor with invalid event
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCopyConstructorWithInvalidEvent() {

        RawEvent sourceEvent = new RawEvent("Invalid", null, null);
        UserLoggedIn event = new UserLoggedIn(sourceEvent);
    }
}
