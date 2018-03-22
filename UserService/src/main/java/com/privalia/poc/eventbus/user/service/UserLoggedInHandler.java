package com.privalia.poc.eventbus.user.service;

import com.privalia.poc.eventbus.user.core.DomainEvent;
import com.privalia.poc.eventbus.user.core.EventHandlerException;
import com.privalia.poc.eventbus.user.event.UserCreated;
import com.privalia.poc.eventbus.user.event.UserLoggedIn;
import com.privalia.poc.eventbus.user.event.UserUpdated;
import com.privalia.poc.eventbus.user.eventbus.EventBusPublisher;
import com.privalia.poc.eventbus.user.eventbus.EventBusPublisherException;
import com.privalia.poc.eventbus.user.model.User;
import com.privalia.poc.eventbus.user.repository.IUserRepository;
import org.hibernate.JDBCException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Random;
import java.util.Set;

/**
 * Handler class for UserLoggedIn event
 *
 * @author david.amigo
 */
@Component
public class UserLoggedInHandler {

    /** Logger */
    private static final Logger LOGGER = LoggerFactory.getLogger(UserLoggedInHandler.class);

    @Autowired
    private Validator validator;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private EventBusPublisher publisher;

    /**
     * Handles the UserLoggedIn event
     *
     * @param userLoggedInEvent the received event
     * @throws EventHandlerException when an error occurred
     */
    public void handle(UserLoggedIn userLoggedInEvent) throws EventHandlerException {

        // Validate the UserLoggerIn event
        Set<ConstraintViolation<DomainEvent>> result = validator.validate(userLoggedInEvent);
        if (result.size() > 0) {
            // Error: Validation error
            LOGGER.error("Event Bus Consumer - Event validation error: " + result.toString() + "");
            return;
        }

        // Find the user in the database
        User userEntity = userRepository.findUserById(userLoggedInEvent.id());
        if (userEntity == null) {

            // Create a new user in the database
            userEntity = newUserFromEvent(userLoggedInEvent);
            insertUser(userEntity);

            // Send user created event
            publishUserCreatedEvent(userEntity);

        } else if (userUpdatedRequired(userEntity, userLoggedInEvent)) {

            // Update global ID if needed
            if (userEntity.getGlobalId() < 1) {
                userEntity.setGlobalId(findGlobalId(userLoggedInEvent.id()));
            }

            // Update the entity data in the database
            userEntity.setFirstName(userLoggedInEvent.firstName());
            userEntity.setLastName(userLoggedInEvent.lastName());
            userEntity.setAddress(userLoggedInEvent.address());
            updateUser(userEntity);

            // Send user updated event
            publishUserUpdatedEvent(userEntity);
        }
    }

    /**
     * Creates an User entity from an UserLoggedIn event
     *
     * @param event the source envent
     * @return an User entity
     */
    private User newUserFromEvent(UserLoggedIn event) {
        long id = event.id();
        return new User(
                id,
                findGlobalId(id),
                event.firstName(),
                event.lastName(),
                event.address()
        );
    }

    /**
     * Determines if an user needs to be updated because its data has changed or it has a bad data
     *
     * @param user  the user entity
     * @param event the event received
     * @return true if an updated is needed
     */
    private boolean userUpdatedRequired(User user, UserLoggedIn event) {

        if (user.getGlobalId() < 1) {
            return true;
        }

        String firstName = (null == event.firstName()) ? "" : event.firstName();
        String lastName = (null == event.lastName()) ? "" : event.lastName();
        String address = (null == event.address()) ? "" : event.address();

        return !user.getFirstName().equals(firstName)
                || !user.getLastName().equals(lastName)
                || !user.getAddress().equals(address);
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
     * Inserts the user in the database
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

    /**
     * Publishes new UserCreated event to the event bus
     *
     * @param user the user data
     * @throws EventHandlerException whan an error publishing
     */
    private void publishUserCreatedEvent(User user) throws EventHandlerException {
        try {
            UserCreated userCreatedEvent = new UserCreated(
                    user.getId(),
                    user.getGlobalId(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getAddress()
            );

            publisher.publish(userCreatedEvent);
        } catch (EventBusPublisherException exc) {
            String message = "User Logged In Event Handler - Error publishing UserCreated event to the event bus!";
            LOGGER.error(message, exc);
            throw new EventHandlerException(message, exc);
        }
    }

    /**
     * Publishes new UserUpdated event to the event bus
     *
     * @param user the user data
     * @throws EventHandlerException whan an error publishing
     */
    private void publishUserUpdatedEvent(User user) throws EventHandlerException {
        try {
            UserUpdated userUpdatedEvent = new UserUpdated(
                    user.getId(),
                    user.getGlobalId(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getAddress()
            );

            publisher.publish(userUpdatedEvent);
        } catch (EventBusPublisherException exc) {
            String message = "User Logged In Event Handler - Error publishing UserUpdated event to the event bus!";
            LOGGER.error(message, exc);
            throw new EventHandlerException(message, exc);
        }
    }

    /**
     * This method should call a web service to obtain the global user id.
     * As this is a PoC, the globalId os the user is randomly generated.
     *
     * @param id the id of the user
     * @return the global user id
     */
    private long findGlobalId(long id) {
        return id + (long)((new Random()).nextInt(1000000));
    }
}
