package com.privalia.poc.eventbus.user.model;

import com.privalia.poc.eventbus.user.event.UserLoggedIn;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The user entity
 *
 * CREATE TABLE `user_service`.`user` (
 *   `id` INT NOT NULL,
 *   `global_id` INT NOT NULL DEFAULT 0,
 *   `first_name` VARCHAR(48) NOT NULL,
 *   `last_name` VARCHAR(72) NOT NULL,
 *   `address` VARCHAR(128) NULL DEFAULT NULL,
 *   PRIMARY KEY (`id`));
 *
 * @author david.amigo
 */
@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(name="id")
    private long id;

    @Column(name="global_id")
    private long globalId;

    @Column(name="first_name", length=48)
    private String firstName;

    @Column(name="last_name", length=72)
    private String lastName;

    @Column(name="address", length=128)
    private String address;

    /**
     * Default constructor
     */
    public User() {
        this.id = 0L;
        this.globalId = 0L;
        this.firstName = "";
        this.lastName = "";
        this.address = "";
    }

    /**
     * Constructor
     *
     * @param id        the id of the user
     * @param globalId  the global id of the user
     * @param firstName the first name of the user
     * @param lastName  the last name of the user
     * @param address   the address of the user
     */
    public User(long id, long globalId, String firstName, String lastName, String address) {
        this.setId(id);
        this.setGlobalId(globalId);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setAddress(address);
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

    /**
     * @return the first name of the user
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the first name of the user
     * @return this
     */
    public User setFirstName(String firstName) {
        this.firstName = (firstName != null) ? firstName : "";
        return this;
    }

    /**
     * @return the last name of the user
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the last name of the user
     * @return this
     */
    public User setLastName(String lastName) {
        this.lastName = (lastName != null) ? lastName  : "";
        return this;
    }

    /**
     * @return the address of the user
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address of the user
     * @return this
     */
    public User setAddress(String address) {
        this.address = (address != null) ? address : "";
        return this;
    }
}