package com.epam.evm.conference.validator;

public class FieldValidator {

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
