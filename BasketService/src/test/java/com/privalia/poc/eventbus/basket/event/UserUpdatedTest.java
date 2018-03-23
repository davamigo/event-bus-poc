package com.privalia.poc.eventbus.basket.event;

import com.privalia.poc.eventbus.basket.core.EventPayload;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Unit test for event class UserUpdated
 *
 * @author david.amigo
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserUpdatedTest {

    /**
     * Test static bindingName()
     */
    @Test
    public void testBindingName() {
        assertEquals("UserUpdated", UserUpdated.bindingName());
    }

    /**
     * Test the constructor
     */
    @Test
    public void testConstructor() {

        UserUpdated event = new UserUpdated(101, 102);

        assertEquals("UserUpdated", event.eventName());
        assertNotNull(event.eventTimestamp());

        assertEquals(101, event.id());
        assertEquals(102, event.globalId());
    }

    /**
     * Test the constructor with null values
     */
    @Test
    public void testConstructorWithNullValues() {

        UserUpdated event = new UserUpdated(0L, 0L);

        assertEquals(0L, event.id());
        assertEquals(0L, event.globalId());
    }

    /**
     * Test event payload getter
     */
    @Test
    public void testEventPayload() {

        UserUpdated event = new UserUpdated(201, 202);

        EventPayload payload = event.eventPayload();
        assertEquals(2, payload.size());
        assertEquals("201", payload.get("id"));
        assertEquals("202", payload.get("global_id"));
    }

    /**
     * Test event payload getter when null values
     */
    @Test
    public void testEventPayloadWhenNullValues() {

        UserUpdated event = new UserUpdated(0L, 0L);

        EventPayload payload = event.eventPayload();
        assertEquals(2, payload.size());
        assertNull(payload.get("id"));
        assertNull(payload.get("global_id"));
    }
}
