package com.privalia.poc.eventbus.fcproxy.core;

import java.util.Date;

/**
 * Interface for a domain event
 *
 * @author david.amigo
 */
public interface DomainEvent {

    /**
     * Returns the event name. I.e: "UserCreated"
     *
     * @return the event name
     */
    String eventName();

    /**
     * Returns the timestamp of the event
     *
     * @return the tiemestamp of the event
     */
    Date eventTimestamp();

    /**
     * Returns the payload of the event as a Map<String, String>
     *
     * @return the payload of the string
     */
    EventPayload eventPayload();
}
