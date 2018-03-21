package com.privalia.poc.eventbus.fcproxy.event;

import com.privalia.poc.eventbus.fcproxy.core.DomainEvent;
import com.privalia.poc.eventbus.fcproxy.core.EventPayload;
import com.privalia.poc.eventbus.fcproxy.core.EventPayloadHashMap;
import com.privalia.poc.eventbus.fcproxy.entity.User;

import java.util.Date;

/**
 * Event which indicates an fcproxy has logged in FC
 *
 * @author david.amigo
 */
public class UserLoggedIn implements DomainEvent {

    /** Event payload */
    private long id;
    private String firstName;
    private String lastName;
    private String address;

    /** Event data */
    private Date timestamp;

    /**
     * Basic Constructor
     *
     * @param id        the id of the fcproxy
     * @param firstName the first name of the fcproxy
     * @param lastName  the last name of the fcproxy
     * @param address   the address of the fcproxy
     */
    public UserLoggedIn(long id, String firstName, String lastName, String address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.timestamp = new Date();
    }

    /**
     * Constructor from an fcproxy entity
     *
     * @param user the fcproxy entity
     */
    public UserLoggedIn(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.address = user.getAddress();
        this.timestamp = new Date();
    }

    /**
     * Returns the event name. I.e: "UserCreated"
     *
     * @return the event name
     */
    @Override
    public String eventName() {
        return "UserLoggedIn";
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
        payload.put("id", (this.id <= 0) ? null : String.valueOf(this.id));
        payload.put("first_name", this.firstName);
        payload.put("last_name", this.lastName);
        payload.put("address", this.address);
        return payload;
    }

    /**
     * @return the id of the fcproxy
     */
    public long id() {
        return this.id;
    }

    /**
     * @return the first name of the fcproxy
     */
    public String firstName() {
        return this.firstName;
    }

    /**
     * @return the last name of the fcproxy
     */
    public String lastName() {
        return this.lastName;
    }

    /**
     * @return the address og the fcproxy
     */
    public String address() {
        return this.address;
    }
}
