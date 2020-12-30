package com.epam.evm.conference.validator;

public interface Validator<T> {

    boolean valid(T field);
}
