package com.epam.evm.conference.utils;

public class DateUtils {

    private final static String DATE_PATTERN = "^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$";
    private final static String TIME_PATTERN = "^([0-1]?[0-9]|2[0-3]):[0-5][0-9](:[0-5][0-9])?$";

    public static boolean isValidDate(String date){
        return date != null && date.matches(DATE_PATTERN);
    }

    public static boolean isValidTime(String time){
        return time != null && time.matches(TIME_PATTERN);
    }
}
