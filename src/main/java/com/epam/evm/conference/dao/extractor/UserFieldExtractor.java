package com.epam.evm.conference.dao.extractor;

import com.epam.evm.conference.model.User;

import java.util.Map;

public class UserFieldExtractor implements FieldExtractor<User>{

    @Override
    public Map<Integer, Object> extractForSave(User entity) {
        return null;
    }

    @Override
    public Map<Integer, Object> extractForUpdate(User entity) {
        return null;
    }
}
