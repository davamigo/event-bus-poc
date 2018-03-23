package com.privalia.poc.eventbus.basket.helper;

import com.privalia.poc.eventbus.basket.core.DomainEvent;
import com.privalia.poc.eventbus.basket.core.EventPayload;
import com.privalia.poc.eventbus.basket.core.EventPayloadHashMap;
import com.privalia.poc.eventbus.basket.core.RawEvent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Unit test for helper class EventSerializer
 *
 * @author david.amigo
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EventSerializerTest {

    /**
     * Test serialize
     */
    @Test
    public void testSerialize() {

        Date currentDate = new Date();

        EventPayload payload = new EventPayloadHashMap();
        payload.put("field1", "201");
        payload.put("field2", "202");

        DomainEvent event = new RawEvent("101", currentDate, payload);

        String expected = "{\"name\":\"101\","
                + "\"timestamp\":\"" + DateSerializer.serialize(event.eventTimestamp()) + "\","
                + "\"payload\":{\"field1\":\"201\",\"field2\":\"202\"}}";

        String result = EventSerializer.serialize(event);
        assertEquals(expected, result);
    }
}
