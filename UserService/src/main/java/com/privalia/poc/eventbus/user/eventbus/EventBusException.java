package com.privalia.poc.eventbus.user.eventbus;

/**
 * Common exception for all event bus classes
 *
 * @author david.amigo
 */
public abstract class EventBusException extends RuntimeException {

    /**
     * Constructs a new event bus runtime exception with the specified detail message.
     *
     * @param message the detail message.
     */
    protected EventBusException(String message) {
        super(message);
    }

    /**
     * Constructs a new event bus runtime exception with the specified detail message and cause.
     *
     * @param message the detail message.
     * @param cause   the cause pf the exception
     */
    protected EventBusException(String message, Throwable cause) {
        super(message, cause);
    }
}
