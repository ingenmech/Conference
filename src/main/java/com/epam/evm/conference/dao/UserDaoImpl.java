package com.epam.evm.conference.dao;

import com.epam.evm.conference.dao.extractor.FieldExtractor;
import com.epam.evm.conference.dao.extractor.UserFieldExtractor;
import com.epam.evm.conference.exception.DaoException;
import com.epam.evm.conference.dao.mapper.RowMapper;
import com.epam.evm.conference.dao.mapper.UserRowMapper;
import com.epam.evm.conference.model.User;

import java.sql.Connection;
import java.util.Optional;

public class UserDaoImpl extends AbstractDao<User> implements UserDao{

    private final static String FIND_BY_LOGIN_AND_PASSWORD = "select * from user where login = ? and password = ?";
    private final static String USER_TABLE = "user";
    private final static RowMapper<User> MAPPER = new UserRowMapper();
    private final static FieldExtractor<User> EXTRACTOR = new UserFieldExtractor();

    public UserDaoImpl(Connection connection) {
        super(connection, MAPPER, EXTRACTOR, USER_TABLE);
    }

    @Override
    public Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException {
        return executeForSingleResult(FIND_BY_LOGIN_AND_PASSWORD, login, password);
    }
}
