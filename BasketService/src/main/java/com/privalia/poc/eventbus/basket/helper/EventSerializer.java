package com.privalia.poc.eventbus.basket.helper;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.privalia.poc.eventbus.basket.core.DomainEvent;

/**
 * Uses Jackson to serialize a domain event
 *
 * @author david.amigo
 */
public class EventSerializer {

    /**
     * Serializes an event
     *
     * @param event the domain event to serialize
     * @return the event converted to a JSON string
     */
    public static String serialize(DomainEvent event) {

        ObjectNode eventData = JsonNodeFactory.instance.objectNode();
        eventData.put("name", event.eventName());
        eventData.put("timestamp", DateSerializer.serialize(event.eventTimestamp()));
        ObjectNode payload = eventData.putObject("payload");
        event.eventPayload().forEach(
                payload::put
        );

        return eventData.toString();
    }
}
