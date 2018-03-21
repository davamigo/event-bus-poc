package com.privalia.poc.eventbus.fcproxy.helper;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Converts a date to a string with a standard format RFC2822
 *
 * @author david.amigo
 */
public class DateSerializer {

    /** Constant for the date time format acording to RFC2822 */
    public static final String FORMAT = "yyyy-MM-dd'T'HH:mm:ssXXX";

    /**
     * Converts a date to a string in RFC2822 format
     *
     * @param date the date to convert
     * @return the date converted to a string
     */
    public static String serialize(Date date) {
        return (new SimpleDateFormat(FORMAT)).format(date);
    }
}
