package com.privalia.poc.eventbus.fcproxy.controller;

import com.privalia.poc.eventbus.fcproxy.entity.User;
import com.privalia.poc.eventbus.fcproxy.event.UserLoggedIn;
import com.privalia.poc.eventbus.fcproxy.eventbus.EventBusPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * API controller
 *
 * @author david.amigo
 */
@RestController
@RequestMapping("/")
public class ApiController {

    @Autowired
    EventBusPublisher publisher;

    /**
     * Controller action called when an fcproxy logged in in the FC app. The post body is the fcproxy data
     *
     * @param user the fcproxy data
     */
    @PostMapping("/user/logged-id")
    public ResponseEntity userLoggedIn(@RequestBody User user) {

        // Publish a message to RabbitMQ
        publisher.publish(new UserLoggedIn(user));

        // Return 200
        return new ResponseEntity(HttpStatus.OK);
    }
}
