package com.epam.evm.conference.validator;

public class FieldUtils {

    private final static String LENGTH_45 = "^.{1,45}$";
    private final static String LENGTH_150 = "^.{1,150}$";
    private final static String LENGTH_300 = "^.{1,300}$";

    public boolean isValidShortLength(String field) {
        return field != null && field.matches(LENGTH_45);
    }

    public boolean isValidMediumLength(String field) {
        return field != null && field.matches(LENGTH_150);
    }

    public boolean isValidLongLength(String field) {
        return field != null && field.matches(LENGTH_300);
    }

    public boolean isValidMediumLength(String[] fields) {
        if (fields == null) {
            return false;
        }
        for (String field : fields) {
            if (field == null || !field.matches(LENGTH_150)) {
                return false;
            }
        }
        return true;
    }
}