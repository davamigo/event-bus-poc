package com.privalia.poc.eventbus.fcproxy.eventbus;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration file for the event bus
 *
 * @author david.amigo
 */
@Configuration
public class EventBusConfig {

    @Value("${eventbus.objects.exchange}")
    private String exchangeName;

    @Bean
    public DirectExchange eventBusExchange() {
        return new DirectExchange(exchangeName, true, false);
    }

    @Bean
    public EventBusPublisher eventBusPublisher() {
        return new EventBusPublisher();
    }
}
