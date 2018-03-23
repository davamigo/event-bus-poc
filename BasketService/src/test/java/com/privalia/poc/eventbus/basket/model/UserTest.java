package com.privalia.poc.eventbus.basket.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

/**
 * Unit test for event class UserCreated
 *
 * @author david.amigo
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

    /**
     * Test the default constructor
     */
    @Test
    public void testDefaultConstructor() {

        User user = new User();

        assertEquals(0L, user.getId());
        assertEquals(0L, user.getGlobalId());
    }

    /**
     * Test the basic constructor
     */
    @Test
    public void testBasicConstructor() {

        User user = new User(101, 102);

        assertEquals(101, user.getId());
        assertEquals(102, user.getGlobalId());
    }

    /**
     * Test the setters with values
     */
    @Test
    public void testSettersWithValues() {

        User user = new User();
        user.setId(201)
                .setGlobalId(202);

        assertEquals(201, user.getId());
        assertEquals(202, user.getGlobalId());
    }

    /**
     * Test the setters with null values
     */
    @Test
    public void testSettersWithNullValues() {

        User user = new User();
        user.setId(-1L)
                .setGlobalId(-1L);

        assertEquals(-1L, user.getId());
        assertEquals(-1L, user.getGlobalId());
    }
}
