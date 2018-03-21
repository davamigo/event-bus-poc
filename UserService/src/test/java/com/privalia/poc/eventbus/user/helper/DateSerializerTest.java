package com.privalia.poc.eventbus.user.helper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Unit test for helper class DateSerializer
 *
 * @author david.amigo
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DateSerializerTest {

    /**
     * Test FORMAT constant
     */
    @Test
    public void testFormatConstant() {
        assertEquals("yyyy-MM-dd'T'HH:mm:ssXXX", DateSerializer.FORMAT);
    }

    /**
     * Test serialize()
     */
    @Test
    public void testSerialize() {

        Date date = new Date(1075882200000L);
        String expected = "2004-02-04T09:10:00+01:00";
        String result = DateSerializer.serialize(date);

        assertEquals(expected, result);
    }
}
