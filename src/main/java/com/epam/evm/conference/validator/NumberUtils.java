package com.epam.evm.conference.validator;

public class NumberUtils {

    private final static String NUMBER_PATTERN = "^\\d*$";

    public boolean isValid(String field, String pattern) {
        return field != null && field.matches(pattern);
    }

    public boolean isValid(String[] fields, String pattern) {
        for (String field : fields){
            if (field == null || !field.matches(pattern)){
                return false;
            }
        }
        return true;
    }
}
