package com.privalia.poc.eventbus.basket.service;

import com.privalia.poc.eventbus.basket.core.EventHandlerException;
import com.privalia.poc.eventbus.basket.event.UserCreated;
import com.privalia.poc.eventbus.basket.event.UserUpdated;
import com.privalia.poc.eventbus.basket.model.User;
import com.privalia.poc.eventbus.basket.repository.IUserRepository;

import org.hibernate.JDBCException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Handler class for UserCreated and UserUpdated events
 *
 * @author david.amigo
 */
@Component
public class UserCreatedOrUpdatedHandler {

    /** Logger */
    private static final Logger LOGGER = LoggerFactory.getLogger(UserCreatedOrUpdatedHandler.class);

    @Autowired
    private IUserRepository userRepository;

    /**
     * Handles the UserCreated event
     *
     * @param userCreatedEvent the received event
     * @throws EventHandlerException when error writing to MySQL
     */
    public void handle(UserCreated userCreatedEvent) throws EventHandlerException {
        handle(userCreatedEvent.id(), userCreatedEvent.globalId());
    }

    /**
     * Handles the UserUpdated event
     *
     * @param userUpdatedEvent the received event
     * @throws EventHandlerException when error writing to MySQL
     */
    public void handle(UserUpdated userUpdatedEvent) throws EventHandlerException {
        handle(userUpdatedEvent.id(), userUpdatedEvent.globalId());
    }

    /**
     * Handles the received event
     *
     * @param userId       The user id
     * @param userGlobalId The user global id
     * @throws EventHandlerException when error writing to MySQL
     */
    private void handle(long userId, long userGlobalId) throws EventHandlerException {

        // Find the user in the database
        User userEntity = userRepository.findUserById(userId);
        if (userEntity == null) {

            // Create a new user in the database
            userEntity = new User(userId, userGlobalId);
            insertUser(userEntity);

        } else if (userEntity.getGlobalId() != userGlobalId) {

            // Update the entity data in the database
            userEntity.setGlobalId(userGlobalId);
            updateUser(userEntity);
        }
    }

    /**
     * Inserts the user in the database
     *
     * @param user the user entity
     * @throws EventHandlerException when an SQL error is detected
     */
    private void insertUser(User user) throws EventHandlerException {
        try {
            userRepository.insert(user);
        } catch (JDBCException exc) {
            String message = "User Logged In Event Handler - SQL error inserting the user!";
            LOGGER.error(message, exc);
            throw new EventHandlerException(message, exc);
        }
    }

    /**
     * Updates the user in the database
     *
     * @param user the user entity
     * @throws EventHandlerException when an SQL error is detected
     */
    private void updateUser(User user) throws EventHandlerException {
        try {
            userRepository.update(user);
        } catch (JDBCException exc) {
            String message = "User Logged In Event Handler - SQL error updating the user!";
            LOGGER.error(message, exc);
            throw new EventHandlerException(message, exc);
        }
    }
}
