package com.privalia.poc.eventbus.fcproxy.event;

import com.privalia.poc.eventbus.fcproxy.core.EventPayload;
import com.privalia.poc.eventbus.fcproxy.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
    }

    /**
     * Test the constructor from fcproxy
     */
    @Test
    public void testConstructorFromUser() {

        User user = new User(201, "202", "203", "204");
        UserLoggedIn event = new UserLoggedIn(user);

        assertEquals(201, event.id());
        assertEquals("202", event.firstName());
        assertEquals("203", event.lastName());
        assertEquals("204", event.address());
    }

    /**
     * Test the get payload method
     */
    @Test
    public void testGetPayload() {

        UserLoggedIn event = new UserLoggedIn(301, "302", "303", "304");

        EventPayload payload = event.eventPayload();
        assertEquals(4, payload.size());
        assertEquals("301", payload.get("id"));
        assertEquals("302", payload.get("first_name"));
        assertEquals("303", payload.get("last_name"));
        assertEquals("304", payload.get("address"));
    }

    /**
     * Test the get payload method with null values
     */
    @Test
    public void testGetPayloadWithNullValues() {

        UserLoggedIn event = new UserLoggedIn(0L, null, null, null);

        EventPayload payload = event.eventPayload();
        assertEquals(4, payload.size());
        assertEquals(null, payload.get("id"));
        assertEquals(null, payload.get("first_name"));
        assertEquals(null, payload.get("last_name"));
        assertEquals(null, payload.get("address"));
    }
}
