package com.privalia.poc.eventbus.basket.eventbus;

/**
 * Class to throw event bus consumer exceptions
 *
 * @author david.amigo
 */
public class EventBusConsumerException extends EventBusException {

    /**
     * Constructs a new event bus consumer runtime exception with the specified detail message.
     *
     * @param message the detail message.
     */
    protected EventBusConsumerException(String message) {
        super(message);
    }

    /**
     * Constructs a new event bus consumer runtime exception with the specified detail message and cause.
     *
     * @param message the detail message.
     * @param cause   the cause pf the exception
     */
    protected EventBusConsumerException(String message, Throwable cause) {
        super(message, cause);
    }
}
