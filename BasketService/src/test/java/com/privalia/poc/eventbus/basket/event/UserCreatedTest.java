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
 * Unit test for event class UserCreated
 *
 * @author david.amigo
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserCreatedTest {

    /**
     * Test static bindingName()
     */
    @Test
    public void testBindingName() {
        assertEquals("UserCreated", UserCreated.bindingName());
    }

    /**
     * Test the constructor
     */
    @Test
    public void testConstructor() {

        UserCreated event = new UserCreated(101, 102);

        assertEquals("UserCreated", event.eventName());
        assertNotNull(event.eventTimestamp());

        assertEquals(101, event.id());
        assertEquals(102, event.globalId());
    }

    /**
     * Test the constructor with null values
     */
    @Test
    public void testConstructorWithNullValues() {

        UserCreated event = new UserCreated(0L, 0L);

        assertEquals(0L, event.id());
        assertEquals(0L, event.globalId());
    }

    /**
     * Test event payload getter
     */
    @Test
    public void testEventPayload() {

        UserCreated event = new UserCreated(201, 202);

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

        UserCreated event = new UserCreated(0L, 0L);

        EventPayload payload = event.eventPayload();
        assertEquals(2, payload.size());
        assertNull(payload.get("id"));
        assertNull(payload.get("global_id"));
    }
}
