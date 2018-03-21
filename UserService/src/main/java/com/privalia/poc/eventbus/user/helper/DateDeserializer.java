package com.privalia.poc.eventbus.user.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Converts a string in a standard format RFC2822 to a Date object
 *
 * @author david.amigo
 */
public class DateDeserializer {

    /** Constant for the date time format acording to RFC2822 */
    public static final String FORMAT = DateSerializer.FORMAT;

    /**
     * Converts a string in RFC2822 format to a Date object
     *
     * @param rawDate the string to convert
     * @return a Date object with the date
     */
    public static Date deserialize(String rawDate) {
        try {
            return (new SimpleDateFormat(FORMAT)).parse(rawDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
