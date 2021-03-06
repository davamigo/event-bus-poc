package com.privalia.poc.eventbus.user.eventbus;

import com.privalia.poc.eventbus.user.event.UserLoggedIn;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
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

    @Value("${eventbus.objects.queue}")
    private String queueName;

    @Bean
    public DirectExchange eventBusExchange() {
        return new DirectExchange(exchangeName, true, false);
    }

    @Bean
    public Queue eventBusQueue() {
        return new Queue(queueName, true, false, false);
    }

    @Bean
    public Binding eventBusBinding(DirectExchange eventBusExchange, Queue eventBusQueue) {
        return BindingBuilder.bind(eventBusQueue).to(eventBusExchange).with((UserLoggedIn.bindingName()));
    }

    @Bean
    public EventBusPublisher eventBusPublisher() {
        return new EventBusPublisher();
    }

    @Bean
    public EventBusConsumer eventBusConsumer() {
        return new EventBusConsumer();
    }
}
