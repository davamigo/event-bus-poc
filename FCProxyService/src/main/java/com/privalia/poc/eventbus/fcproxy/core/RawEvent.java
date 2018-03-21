package com.privalia.poc.eventbus.fcproxy.core;

import java.util.Date;

/**
 * Raw domain event read from a queue
 *
 * @author david.amigo
 */
public class RawEvent implements DomainEvent {

    private String name;
    private Date timestamp;
    private EventPayload payload;

    /**
     * Constructor
     *
     * @param name      the name of the event
     * @param timestamp the timestamp of the event
     * @param payload   the payload of the event
     */
    public RawEvent(String name, Date timestamp, EventPayload payload) {
        this.name = name;
        this.timestamp = timestamp;
        this.payload = payload;
    }

    /**
     * Returns the event name. I.e: "UserCreated"
     *
     * @return the event name
     */
    @Override
    public String eventName() {
        return name;
    }

    /**
     * Returns the timestamp of the event
     *
     * @return the tiemestamp of the event
     */
    @Override
    public Date eventTimestamp() {
        return timestamp;
    }

    /**
     * Returns the payload of the event as a Map<String, String>
     *
     * @return the payload of the string
     */
    @Override
    public EventPayload eventPayload() {
        return payload;
    }
}
