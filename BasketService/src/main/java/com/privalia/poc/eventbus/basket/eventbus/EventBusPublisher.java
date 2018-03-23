package com.privalia.poc.eventbus.basket.eventbus;

import com.privalia.poc.eventbus.basket.core.DomainEvent;
import com.privalia.poc.eventbus.basket.helper.EventSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Publisher class for the event bus
 *
 * @author david.amigo
 */
public class EventBusPublisher {

    /** Logger */
    private static final Logger LOGGER = LoggerFactory.getLogger(EventBusPublisher.class);

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private DirectExchange eventBusExchange;

    /**
     * Publishes an event to the event bus
     *
     * @param  event the event data
     * @throws EventBusPublisherException when an error occurred publishing
     */
    public void publish(DomainEvent event) throws EventBusPublisherException {

        String exchange = eventBusExchange.getName();
        String eventKey = event.eventName();
        String eventData = EventSerializer.serialize(event);

        LOGGER.info(
                "Event Bus Publisher - Publishing an event:" +
                "\n\tExchange=\"" + exchange + "\"" +
                "\n\tKey=\"" + eventKey + "\"" +
                "\n\tData=\"" + eventData + "\""
        );

        try {
            template.convertAndSend(exchange, eventKey, eventData);
        } catch (AmqpException exc) {
            String errorMsg = "Event Bus Publisher - Error occurred publishing an event!";
            LOGGER.debug(errorMsg, exc);
            throw new EventBusPublisherException(errorMsg, exc);
        }
    }
}
