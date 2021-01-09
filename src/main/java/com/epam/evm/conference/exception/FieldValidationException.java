package com.epam.evm.conference.exception;

public class FieldValidationException extends ServiceException{

    private final String fieldName;

    public FieldValidationException(String fieldName, String message) {
        super(message);
        this.fieldName = fieldName;
    }

    public FieldValidationException(String message) {
        super(message);
        fieldName = "";
    }

    public String getFieldName() {
        return fieldName;
    }
}
