package com.privalia.poc.eventbus.fcproxy.eventbus;

/**
 * Class to throw event bus publisher exceptions
 *
 * @author david.amigo
 */
public class EventBusPublisherException extends EventBusException {

    /**
     * Constructs a new event bus publisher runtime exception with the specified detail message.
     *
     * @param message the detail message.
     */
    protected EventBusPublisherException(String message) {
        super(message);
    }

    /**
     * Constructs a new event bus publisher runtime exception with the specified detail message and cause.
     *
     * @param message the detail message.
     * @param cause   the cause pf the exception
     */
    protected EventBusPublisherException(String message, Throwable cause) {
        super(message, cause);
    }
}
