package com.epam.evm.conference.validator;

public class DateUtils {

    private final static String datePattern = "^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$";
    private final static String timePattern = "^([0-1]?[0-9]|2[0-3]):[0-5][0-9](:[0-5][0-9])?$";

    public boolean isValidDate(String date){
        return date != null && date.matches(datePattern);
    }

    public boolean isValidTime(String time){
        return time != null && time.matches(timePattern);
    }
}
