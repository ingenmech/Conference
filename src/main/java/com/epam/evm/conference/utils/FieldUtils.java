package com.epam.evm.conference.utils;

public class FieldUtils {

    private final static String PATTERN = "^.{1,%d}$";

    public final static int SHORT_SIZE = 45;
    public final static int MID_SIZE = 150;
    public final static int LONG_SIZE = 300;

    public static boolean isValidLength(String field, int length) {
        return field != null && field.matches(String.format(PATTERN, length));
    }

    public static boolean isValidArrayElementsLength(String[] fields, int length) {
        if (fields == null) {
            return false;
        }
        for (String field : fields) {
            if (field == null || !field.matches(String.format(PATTERN, length))) {
                return false;
            }
        }
        return true;
    }
}