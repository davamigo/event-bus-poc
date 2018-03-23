package com.privalia.poc.eventbus.basket.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * The user entity
 *
 * CREATE TABLE `basket_service`.`user` (
 *   `id` INT NOT NULL,
 *   `global_id` INT NOT NULL
 *   PRIMARY KEY (`id`));
 *
 * @author david.amigo
 */
@Entity
@Table(name = "user")
public class User {

    @NotNull
    @Min(1)
    @Id
    @Column(name="id")
    private long id;

    @NotNull
    @Column(name="global_id")
    private long globalId;

    /**
     * Default constructor
     */
    public User() {
        this.id = 0L;
        this.globalId = 0L;
    }

    /**
     * Constructor
     *
     * @param id        the id of the user
     * @param globalId  the global id of the user
     */
    public User(long id, long globalId) {
        this.setId(id);
        this.setGlobalId(globalId);
    }

    /**
     * @return Get id of the user
     */
    public long getId() {
        return id;
    }

    /**
     * @param id Get id of the user
     * @return this
     */
    public User setId(long id) {
        this.id = id;
        return this;
    }

    /**
     * @return the global id of the user
     */
    public long getGlobalId() {
        return globalId;
    }

    /**
     * @param globalId the global id of the user
     * @return this
     */
    public User setGlobalId(long globalId) {
        this.globalId = globalId;
        return this;
    }
}
