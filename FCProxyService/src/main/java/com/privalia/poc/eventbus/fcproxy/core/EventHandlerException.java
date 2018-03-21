package com.privalia.poc.eventbus.fcproxy.core;

/**
 * Exception to throw when an error occurred handling an envent received
 *
 * @author david.amigo
 */
public class EventHandlerException extends RuntimeException {

    /**
     * Constructs a new event handler runtime exception with the specified detail message.
     *
     * @param message the detail message.
     */
    public EventHandlerException(String message) {
        super(message);
    }

    /**
     * Constructs a new event handler runtime exception with the specified detail message and cause.
     *
     * @param message the detail message.
     * @param cause   the cause pf the exception
     */
    public EventHandlerException(String message, Throwable cause) {
        super(message, cause);
    }
}
