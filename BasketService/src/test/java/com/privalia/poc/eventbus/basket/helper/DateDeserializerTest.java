package com.privalia.poc.eventbus.basket.helper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Unit test for helper class DateDeserializer
 *
 * @author david.amigo
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DateDeserializerTest {

    /**
     * Test deserialize()
     */
    @Test
    public void testDeserialize() {

        String rawDate = "2004-02-04T09:10:00+01:00";
        Date expected = new Date(1075882200000L);
        Date result = DateDeserializer.deserialize(rawDate);

        assertEquals(expected, result);
    }
}
