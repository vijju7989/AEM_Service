package com.sealedair.utils;

import java.util.Calendar;

/**
 * Simple utilities for working with Dates.
 */
public final class DateUtils {

    private DateUtils() {
        throw new IllegalStateException("Utility Class");
    }

    public static int getYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }
}
