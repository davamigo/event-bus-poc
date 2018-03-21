package com.privalia.poc.eventbus.fcproxy.entity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Unit test for entity class User
 *
 * @author david.amigo
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

    /**
     * Test empty constructor
     */
    @Test
    public void testEmptyConstructor() {

        User user = new User();

        assertEquals(0L, user.getId());
        assertNull(user.getFirstName());
        assertNull(user.getLastName());
        assertNull(user.getAddress());
    }

    /**
     * Test basic constructor
     */
    @Test
    public void testBasicConstructor() {

        User user = new User(101, "102", "103", "104");

        assertEquals(101, user.getId());
        assertEquals("102", user.getFirstName());
        assertEquals("103", user.getLastName());
        assertEquals("104", user.getAddress());
    }

    /**
     * Test setters
     */
    @Test
    public void testSetters() {

        User user = new User();

        user.setId(101)
                .setFirstName("102")
                .setLastName("103")
                .setAddress("104");

        assertEquals(101, user.getId());
        assertEquals("102", user.getFirstName());
        assertEquals("103", user.getLastName());
        assertEquals("104", user.getAddress());
    }
}
