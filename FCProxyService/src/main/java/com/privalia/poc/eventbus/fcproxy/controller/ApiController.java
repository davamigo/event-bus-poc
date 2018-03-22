package com.privalia.poc.eventbus.fcproxy.controller;

import com.privalia.poc.eventbus.fcproxy.entity.User;
import com.privalia.poc.eventbus.fcproxy.event.UserLoggedIn;
import com.privalia.poc.eventbus.fcproxy.eventbus.EventBusPublisher;
import com.privalia.poc.eventbus.fcproxy.eventbus.EventBusPublisherException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import javax.validation.Valid;

/**
 * API controller
 *
 * @author david.amigo
 */
@RestController
@RequestMapping("/")
public class ApiController {

    /** Logger */
    private static final Logger LOGGER = LoggerFactory.getLogger(ApiController.class);

    @Autowired
    EventBusPublisher publisher;

    /**
     * Controller action called when an fcproxy logged in in the FC app. The post body is the fcproxy data
     *
     * @param user the fcproxy data
     */
    @PostMapping("/user/logged-id")
    public ResponseEntity userLoggedIn(@RequestBody @Valid User user) {

        LOGGER.info("Api Controller - User logged in: " + user.toString());

        // Publish an UserLoggedIn event to the event bus
        try {
            publisher.publish(new UserLoggedIn(user));
        } catch (EventBusPublisherException exc) {
            String message = "Api Controller - Error publishing to the event bus!";
            LOGGER.error(message, exc);
            throw new RestClientException(message);
        }

        // Return HTTP code 200
        return new ResponseEntity(HttpStatus.OK);
    }
}
