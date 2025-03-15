package com.example.demo.utils;

import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Utilities for the Date and Time
 */
@UtilityClass
public class DateTimeUtils {

    private static final String DEFAULT_DATE_TIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSX";

    /**
     * Parses a string using the {@link #DEFAULT_DATE_TIME_PATTERN}
     *
     * @param time A date time string
     * @return The date time as {@link LocalDateTime}
     */
    public static LocalDateTime parse(String time) {
        return parse(time, DEFAULT_DATE_TIME_PATTERN);
    }

    /**
     * Parses a string using the pattern given
     *
     * @param time    A date time string
     * @param pattern The pattern to use to parse the string
     * @return The date time as {@link LocalDateTime}
     */
    public static LocalDateTime parse(String time, String pattern) {
        return LocalDateTime.parse(time, DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * Checks whether a string can be parsed using the pattern given
     *
     * @param time    A date time string
     * @param pattern The pattern to use to parse the string
     * @return true if the parsing was successful, false otherwise
     */
    public static boolean isValidDateFormat(String time, String pattern) {
        try {
            parse(time, pattern);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Checks whether a string can be parsed using the {@link #DEFAULT_DATE_TIME_PATTERN}
     *
     * @param time A date time string
     * @return true if the parsing was successful, false otherwise
     */
    public static boolean isValidDateFormat(String time) {
        return isValidDateFormat(time, DEFAULT_DATE_TIME_PATTERN);
    }
}
