package com.privalia.poc.eventbus.user.helper;

import com.privalia.poc.eventbus.user.core.DomainEvent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Unit test for helper class EventDeserializer
 *
 * @author david.amigo
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EventDeserializerTest {

    /**
     * Test deserialize
     */
    @Test
    public void testDeserialize() {

        Date date = new Date(1514761200000L);

        String rawEvent = "{\"name\":\"101\","
                + "\"timestamp\":\"" + DateSerializer.serialize(date) + "\","
                + "\"payload\":{\"field1\":\"201\",\"field2\":\"202\"}}";

        try {
            DomainEvent event = EventDeserializer.deserialize(rawEvent);

            assertEquals("101", event.eventName());
            assertEquals(date.getTime(), event.eventTimestamp().getTime());
            assertEquals(2, event.eventPayload().size());
            assertEquals("201", event.eventPayload().get("field1"));
            assertEquals("202", event.eventPayload().get("field2"));
        } catch (IOException e) {
            e.printStackTrace();
            assertTrue(false);
        }
    }
}
