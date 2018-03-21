package com.privalia.poc.eventbus.user.event;

import com.privalia.poc.eventbus.user.core.EventPayload;
import com.privalia.poc.eventbus.user.core.EventPayloadHashMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Unit test for event class UserCreated
 *
 * @author david.amigo
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserCreatedTest {

    /**
     * Test the constructor
     */
    @Test
    public void testConstructor() {

        UserCreated event = new UserCreated(100, 101, "102", "103", "104");

        assertEquals(100, event.id());
        assertEquals(101, event.globalId());
        assertEquals("102", event.firstName());
        assertEquals("103", event.lastName());
        assertEquals("104", event.address());

        assertEquals("UserCreated", event.eventName());

        EventPayload payload = event.eventPayload();
        assertEquals("100", payload.get("id"));
        assertEquals("101", payload.get("global_id"));
        assertEquals("102", payload.get("first_name"));
        assertEquals("103", payload.get("last_name"));
        assertEquals("104", payload.get("address"));
    }

    /**
     * Test the constructor with null values
     */
    @Test
    public void testConstructorWithNullValues() {

        UserCreated event = new UserCreated(0L, 0L, null, null, null);

        assertEquals(0L, event.id());
        assertEquals(0L, event.globalId());
        assertNull(event.firstName());
        assertNull(event.lastName());
        assertNull(event.address());

        EventPayload payload = event.eventPayload();
        assertEquals(null, payload.get("id"));
        assertEquals(null, payload.get("global_id"));
        assertEquals(null, payload.get("first_name"));
        assertEquals(null, payload.get("last_name"));
        assertEquals(null, payload.get("address"));
    }
}
