package com.privalia.poc.eventbus.user.eventbus;

import com.privalia.poc.eventbus.user.core.DomainEvent;
import com.privalia.poc.eventbus.user.event.UserLoggedIn;
import com.privalia.poc.eventbus.user.helper.EventDeserializer;
import com.privalia.poc.eventbus.user.service.UserLoggedInHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
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
    private static final Logger LOGGER = LoggerFactory.getLogger(EventBusConsumer.class);

    @Autowired
    private UserLoggedInHandler userLoggedInHandler;

    /**
     * RabbitMQ consumer - called wheneven an event arrives
     *
     * @param message the raw data of the event
     * @throws EventBusConsumerException when an erroro occurred
     */
    @RabbitListener(queues = "#{eventBusQueue.name}")
    public void consume(Message message) throws EventBusConsumerException {

        String rawEvent = new String(message.getBody());

        LOGGER.info(
                "Event Bus Consumer - Consuming an event." +
                "\n\tRaw data=\"" + rawEvent + "\""
        );

        DomainEvent event = null;
        try {
            event = EventDeserializer.deserialize(rawEvent);
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
}
