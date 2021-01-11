package com.epam.evm.conference.validator;

public class NumberUtils {

    private final static String NUMBER_PATTERN = "^\\d*$";

    public boolean isValidDigit(String field) {
        return field != null && field.matches(NUMBER_PATTERN);
    }

    public boolean isValidDigit(String[] fields) {
        if (fields == null) {
            return false;
        }
        for (String field : fields) {
            if (field == null || !field.matches(NUMBER_PATTERN)) {
                return false;
            }
        }
        return true;
    }
}
