package com.privalia.poc.eventbus.fcproxy.entity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

/**
 * The user entity
 *
 * @author david.amigo
 */
public class User {

    @NotNull
    @Min(1)
    private long id;

    @NotNull
    @NotEmpty
    @Size(max=48)
    private String firstName;

    @NotNull
    @NotEmpty
    @Size(max=72)
    private String lastName;

    @Size(max=128)
    private String address;

    /**
     * Default constructor
     */
    public User() {
        this.id = 0L;
        this.firstName = null;
        this.lastName = null;
        this.address = null;
    }

    /**
     * Constructor
     *
     * @param id        the id of the user
     * @param firstName the first name of the user
     * @param lastName  the last name of the user
     * @param address   the address of the user
     */
    public User(long id, String firstName, String lastName, String address) {
        this.setId(id);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setAddress(address);
    }

    /**
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "{id=" + id +
                ", firstName=\"" + firstName + "\"" +
                ", lastName=\"" + lastName + "\"" +
                ", address=\"" + address + "\"}";
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
        this.firstName = firstName;
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
        this.lastName = lastName;
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
        this.address = address;
        return this;
    }
}
