package com.privalia.poc.eventbus.fcproxy.helper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.privalia.poc.eventbus.fcproxy.core.DomainEvent;
import com.privalia.poc.eventbus.fcproxy.core.EventPayload;
import com.privalia.poc.eventbus.fcproxy.core.EventPayloadHashMap;
import com.privalia.poc.eventbus.fcproxy.core.RawEvent;

import java.io.IOException;
import java.util.Date;

/**
 * Uses Jackson to deserialize a domain event
 *
 * @author david.amigo
 */
public class EventDeserializer {

    /**
     * Deserializes an event
     *
     * @param rawEvent the domain event to deserialize
     * @return the event deserialized
     */
    public static DomainEvent deserialize(String rawEvent) throws IOException {

        ObjectMapper mapper = new ObjectMapper();

        JsonNode node = mapper.readTree(rawEvent);
        String name = node.get("name").asText(null);

        String eventTimestamp = node.get("timestamp").asText(null);
        Date timestamp = (eventTimestamp == null) ? null : DateDeserializer.deserialize(eventTimestamp);

        EventPayload payload = new EventPayloadHashMap();
        node.get("payload").fields().forEachRemaining(
                field -> payload.put(field.getKey(), field.getValue().asText(null))
        );

        return new RawEvent(name, timestamp, payload);
    }
}
