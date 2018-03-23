package com.privalia.poc.eventbus.basket.event;

import com.privalia.poc.eventbus.basket.core.DomainEvent;

/**
 * Event which indicates an user has been updated in our database
 *
 * @author david.amigo
 */
public class UserUpdated extends UserCreated implements DomainEvent {

    /**
     * Constructor
     *
     * @param id        the id of the user
     * @param globalId  the global id of the user
     */
    public UserUpdated(long id, long globalId) {
        super(id, globalId);
    }


    /**
     * Copy Constructor
     *
     * @param event the source envent
     * @throws IllegalArgumentException the the event name doesn't match
     */
    public UserUpdated(DomainEvent event) throws IllegalArgumentException {
        super(event);
    }

    /**
     * Get the event name
     *
     * @return the event name
     */
    public static String bindingName() {
        return "UserUpdated";
    }

    /**
     * Returns the event name
     *
     * @return the event name
     */
    @Override
    public String eventName() {
        return UserUpdated.bindingName();
    }
}
