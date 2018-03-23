package com.privalia.poc.eventbus.basket.repository;

import com.privalia.poc.eventbus.basket.model.User;

/**
 * Interface for the user repository
 *
 * @author david.amigo
 */
public interface IUserRepository {

    /**
     * Finds an user by id
     *
     * @param id the id to find
     * @return the User object or null
     */
    User findUserById(long id);

    /**
     * Inserts an user in the database
     *
     * @param user the user object to insert
     * @return the user object inserted
     */
    User insert(User user);

    /**
     * Updates an user in the database
     *
     * @param user the user object to update
     * @return the user object updated
     */
    User update(User user);
}
