package com.privalia.poc.eventbus.user.core;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Unit test for event class RawEvent
 *
 * @author david.amigo
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RawEventTest {

    /**
     * Test the constructor
     */
    @Test
    public void testConstructor() {
        Date date = new Date();

        EventPayload payload = new EventPayloadHashMap();
        payload.put("field", "201");

        DomainEvent event = new RawEvent("101", date, payload);

        assertEquals("101", event.eventName());
        assertEquals(date, event.eventTimestamp());

        EventPayload eventPayload = event.eventPayload();
        assertEquals(1, eventPayload.size());
        assertEquals("201", eventPayload.get("field"));
    }
}
