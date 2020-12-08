package com.epam.evm.conference.dao.extractor;

import com.epam.evm.conference.model.User;

import java.util.Map;

public class UserFieldExtractor implements FieldExtractor<User>{

    @Override
    public Map<Integer, Object> extract(User entity) {
        return null;
    }
}
