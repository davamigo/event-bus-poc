package com.privalia.poc.eventbus.basket.repository;

import com.privalia.poc.eventbus.basket.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 * The user repository
 *
 * @author david.amigo
 */
@Repository
@Transactional
public class UserRepository implements IUserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Finds an user by id
     *
     * @param id the id to find
     * @return the user object found or null
     */
    @Override
    public User findUserById(long id) {
        return entityManager.find(User.class, id);
    }

    /**
     * Inserts an user in the database
     *
     * @param user the user object to insert
     * @return the user object inserted
     */
    @Override
    public User insert(User user) {
        entityManager.persist(user);
        entityManager.flush();
        return user;
    }

    /**
     * Updates an user in the database
     *
     * @param user the user object to update
     * @return the user object updated
     */
    public User update(User user) {
        User result = entityManager.merge(user);
        entityManager.flush();
        return result;
    }
}
