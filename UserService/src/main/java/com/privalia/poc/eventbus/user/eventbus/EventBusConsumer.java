package com.privalia.poc.eventbus.user.eventbus;

import com.privalia.poc.eventbus.user.core.DomainEvent;
import com.privalia.poc.eventbus.user.event.UserLoggedIn;
import com.privalia.poc.eventbus.user.helper.EventDeserializer;
import com.privalia.poc.eventbus.user.service.UserLoggedInHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

/**
 * Publisher class for the event bus
 *
 * @author david.amigo
 */
public class EventBusConsumer {

    /** Logger */
    private static final Logger LOGGER = LoggerFactory.getLogger(EventBusPublisher.class);

    @Autowired
    private UserLoggedInHandler userLoggedInHandler;

    /**
     * RabbitMQ consumer - called wheneven an event arrives
     *
     * @param rawEvent the raw data of the event
     * @throws EventBusConsumerException when an erroro occurred
     */
    @RabbitListener(queues = "#{eventBusQueue.name}")
    public void consume(String rawEvent) throws EventBusConsumerException{

        LOGGER.info(
                "Event Bus Consumer - Consuming an event." +
                "\n\tRaw data=\"" + rawEvent + "\""
        );

        DomainEvent event = null;
        try {
            event = decodeEvent(rawEvent);
        } catch (IOException exc) {
            LOGGER.error("Event Bus Consumer - Error occurred decoding the event received!", exc);
            return;
        }

        String eventName = event.eventName();
        if (eventName.equals(UserLoggedIn.bindingName())) {

            // Handle UserLoggedIn event
            UserLoggedIn userLoggedInEvent = new UserLoggedIn(event);
            userLoggedInHandler.handle(userLoggedInEvent);
        } else {

            // Error: Unrecognised event
            LOGGER.error("Event Bus Consumer - Unrecognised event: \"" + eventName + "\"!");
            return;
        }
    }

    /**
     * Decodes a received event
     *
     * @param rawEvent a string containing the raw event data
     * @return a RawEvent object with the event data
     * @throws IOException when error deserializing
     */
    private DomainEvent decodeEvent(String rawEvent) throws IOException {

        String eventDecoded;
        try {
            StringBuilder eventData = new StringBuilder();
            String[] nums = rawEvent.split("\\,");
            for (String num : nums) {
                int n = Integer.parseInt(num);
                eventData.append((char) n);
            }

            eventDecoded = eventData.toString();

            LOGGER.info(
                    "Event Bus Consumer - Event decoded." +
                    "\n\tEvent data=\"" + eventDecoded + "\""
            );
        } catch (java.lang.NumberFormatException exc) {
            eventDecoded = rawEvent;
        }

        return EventDeserializer.deserialize(eventDecoded);
    }
}
