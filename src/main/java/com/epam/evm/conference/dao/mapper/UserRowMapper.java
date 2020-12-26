package com.epam.evm.conference.dao.mapper;

import com.epam.evm.conference.exception.DaoException;
import com.epam.evm.conference.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    private final static String ID = "id";
    private final static String ROLE = "role";
    private final static String LOGIN = "login";
    private final static String PASSWORD = "password";

    @Override
    public User map(ResultSet resultSet) throws DaoException {

        try {
            Long id = resultSet.getLong(ID);
            String role = resultSet.getString(ROLE);
            String login = resultSet.getString(LOGIN);
            String password = resultSet.getString(PASSWORD);
            return new User(id, role, login, password);
        } catch (SQLException e) {
            throw new DaoException("Error UserRowMapper", e);
        }
    }
}
