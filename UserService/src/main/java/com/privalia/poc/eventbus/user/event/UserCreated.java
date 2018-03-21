package com.privalia.poc.eventbus.user.event;

import com.privalia.poc.eventbus.user.core.DomainEvent;
import com.privalia.poc.eventbus.user.core.EventPayload;
import com.privalia.poc.eventbus.user.core.EventPayloadHashMap;

import java.util.Date;

/**
 * Event which indicates an user has been inserted in our database
 *
 * @author david.amigo
 */
public class UserCreated implements DomainEvent {

    /** Event payload */
    private long id;
    private long globalId;
    private String firstName;
    private String lastName;
    private String address;

    /** Event data */
    private Date timestamp;

    /**
     * Constructor
     *
     * @param id        the id of the user
     * @param globalId  the global id of the user
     * @param firstName the first name of the user
     * @param lastName  the last name of the user
     * @param address   the address of the user
     */
    public UserCreated(long id, long globalId, String firstName, String lastName, String address) {
        this.id = id;
        this.globalId = globalId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.timestamp = new Date();
    }

    /**
     * Get the event name
     *
     * @return the event name
     */
    public static String bindingName() {
        return "UserCreated";
    }

    /**
     * Returns the event name. I.e: "UserCreated"
     *
     * @return the event name
     */
    @Override
    public String eventName() {
        return bindingName();
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
        EventPayload payload = new EventPayloadHashMap();
        payload.put("id", (id <= 0) ? null : String.valueOf(this.id));
        payload.put("global_id", (globalId) <= 0 ? null : String.valueOf(this.globalId));
        payload.put("first_name", this.firstName);
        payload.put("last_name", this.lastName);
        payload.put("address", this.address);
        return payload;
    }

    /**
     * @return the id of the user
     */
    public long id() {
        return this.id;
    }

    /**
     * @return the global id of the user
     */
    public long globalId() {
        return this.globalId;
    }

    /**
     * @return the first name of the user
     */
    public String firstName() {
        return this.firstName;
    }

    /**
     * @return the last name of the user
     */
    public String lastName() {
        return this.lastName;
    }

    /**
     * @return the address og the user
     */
    public String address() {
        return this.address;
    }
}
