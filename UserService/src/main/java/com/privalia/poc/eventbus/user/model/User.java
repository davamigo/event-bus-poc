package com.privalia.poc.eventbus.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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

    @NotNull
    @Min(1)
    @Id
    @Column(name="id")
    private long id;

    @NotNull
    @Column(name="global_id")
    private long globalId;

    @NotEmpty
    @NotNull
    @Size(max=48)
    @Column(name="first_name", length=48)
    private String firstName;

    @NotEmpty
    @NotNull
    @Size(max=72)
    @Column(name="last_name", length=72)
    private String lastName;

    @Size(max=128)
    @Column(name="address", length=128)
    private String address;

    /**
     * Default constructor
     */
    public User() {
        this.setId(0L);
        this.setGlobalId(0L);
        this.setFirstName(null);
        this.setLastName(null);
        this.setAddress(null);
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
