package com.privalia.poc.eventbus.user.event;

import com.privalia.poc.eventbus.user.core.DomainEvent;
import com.privalia.poc.eventbus.user.core.EventPayload;
import com.privalia.poc.eventbus.user.core.EventPayloadHashMap;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.Date;

/**
 * Event which indicates an user has logged in the primary system
 *
 * @author david.amigo
 */
public class UserLoggedIn implements DomainEvent {

    /** Event payload */

    @Min(1)
    private long id;

    @NotNull
    @NotEmpty
    @Size(max=48)
    private String firstName;

    @NotNull
    @NotEmpty
    @Size(max=72)
    private String lastName;

    @Size(max=128)
    private String address;

    /** Event meta data */

    @NotNull
    private Date timestamp;

    /**
     * Basic Constructor
     *
     * @param id        the id of the user
     * @param firstName the first name of the user
     * @param lastName  the last name of the user
     * @param address   the address of the user
     */
    public UserLoggedIn(long id, String firstName, String lastName, String address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.timestamp = new Date();
    }

    /**
     * Copy Constructor
     *
     * @param event the source envent
     * @throws IllegalArgumentException the the event name doesn't match
     */
    public UserLoggedIn(DomainEvent event) throws IllegalArgumentException {
        if (!event.eventName().equals(bindingName())) {
            throw new IllegalArgumentException("The source event must be a " + bindingName());
        }

        EventPayload payload = event.eventPayload();
        if (null == payload) {
            this.id = -1L;
            this.firstName = null;
            this.lastName = null;
            this.address = null;
        } else {
            String tempId = payload.get("id");
            this.id = (null == tempId) ? -1L : Long.parseLong(tempId);
            this.firstName = payload.get("first_name");
            this.lastName = payload.get("last_name");
            this.address = payload.get("address");
        }

        this.timestamp = event.eventTimestamp();

    }

    /**
     * Get the event name
     *
     * @return the event name
     */
    public static String bindingName() {
        return "UserLoggedIn";
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
        payload.put("id", (this.id <= 0) ? null : String.valueOf(this.id));
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
