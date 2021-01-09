package com.epam.evm.conference.dao.extractor;

import com.epam.evm.conference.model.User;

import java.util.HashMap;
import java.util.Map;

public class UserFieldExtractor implements FieldExtractor<User>{

    private final static String ROLE = "role";
    private final static String LOGIN = "login";
    private final static String PASSWORD = "password";

    @Override
    public Map<String, Object> extract(User entity) {

        String role = entity.getRole();
        String login = entity.getLogin();
        String password = entity.getPassword();

        Map<String, Object> map = new HashMap<>();
        map.put(ROLE, role);
        map.put(LOGIN, login);
        map.put(PASSWORD, password);
        return map;
    }

}
