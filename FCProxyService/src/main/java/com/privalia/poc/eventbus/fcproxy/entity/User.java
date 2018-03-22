package com.privalia.poc.eventbus.fcproxy.entity;

/**
 * The fcproxy entity
 *
 * @author david.amigo
 */
public class User {
    private long id;
    private String firstName;
    private String lastName;
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
     * @param id        the id of the fcproxy
     * @param firstName the first name of the fcproxy
     * @param lastName  the last name of the fcproxy
     * @param address   the address of the fcproxy
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
     * @return Get id of the fcproxy
     */
    public long getId() {
        return id;
    }

    /**
     * @param id Get id of the fcproxy
     * @return this
     */
    public User setId(long id) {
        this.id = id;
        return this;
    }

    /**
     * @return the first name of the fcproxy
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the first name of the fcproxy
     * @return this
     */
    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    /**
     * @return the last name of the fcproxy
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the last name of the fcproxy
     * @return this
     */
    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    /**
     * @return the address of the fcproxy
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address of the fcproxy
     * @return this
     */
    public User setAddress(String address) {
        this.address = address;
        return this;
    }
}
