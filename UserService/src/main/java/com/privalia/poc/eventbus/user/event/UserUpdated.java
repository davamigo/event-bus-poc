package com.privalia.poc.eventbus.user.event;

import com.privalia.poc.eventbus.user.core.DomainEvent;

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
     * @param firstName the first name of the user
     * @param lastName  the last name of the user
     * @param address   the address of the user
     */
    public UserUpdated(long id, long globalId, String firstName, String lastName, String address) {
        super(id, globalId, firstName, lastName, address);
    }

    /**
     * Returns the event name
     *
     * @return the event name
     */
    @Override
    public String eventName() {
        return "UserUpdated";
    }
}
