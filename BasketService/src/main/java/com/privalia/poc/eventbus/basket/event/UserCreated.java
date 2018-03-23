package com.privalia.poc.eventbus.basket.event;

import com.privalia.poc.eventbus.basket.core.DomainEvent;
import com.privalia.poc.eventbus.basket.core.EventPayload;
import com.privalia.poc.eventbus.basket.core.EventPayloadHashMap;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Event which indicates an user has been inserted in our database
 *
 * @author david.amigo
 */
public class UserCreated implements DomainEvent {

    /** Event payload */

    @Min(1)
    private long id;

    private long globalId;

    /** Event data */

    @NotNull
    private Date timestamp;

    /**
     * Basic constructor
     *
     * @param id        the id of the user
     * @param globalId  the global id of the user
     */
    public UserCreated(long id, long globalId) {
        this.id = id;
        this.globalId = globalId;
        this.timestamp = new Date();
    }

    /**
     * Copy Constructor
     *
     * @param event the source envent
     * @throws IllegalArgumentException the the event name doesn't match
     */
    public UserCreated(DomainEvent event) throws IllegalArgumentException {
        if (!event.eventName().equals(UserCreated.bindingName())
                && !event.eventName().equals(UserUpdated.bindingName())) {
            throw new IllegalArgumentException("The source event must be a " + UserCreated.bindingName() + " or " + UserUpdated.bindingName());
        }

        EventPayload payload = event.eventPayload();
        if (null == payload) {
            this.id = -1L;
            this.globalId = -1L;
        } else {
            String tempId;
            this.id = (null == (tempId = payload.get("id"))) ? -1L : Long.parseLong(tempId);
            this.globalId = (null == (tempId = payload.get("global_id"))) ? -1L : Long.parseLong(tempId);
        }

        this.timestamp = event.eventTimestamp();

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
        return UserCreated.bindingName();
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
}
