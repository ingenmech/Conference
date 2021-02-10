package com.epam.evm.conference.dao;

import com.epam.evm.conference.dao.daoInterface.UserPersistentDao;
import com.epam.evm.conference.dao.extractor.FieldExtractor;
import com.epam.evm.conference.dao.extractor.UserFieldExtractor;
import com.epam.evm.conference.dao.mapper.RowMapper;
import com.epam.evm.conference.dao.mapper.UserRowMapper;
import com.epam.evm.conference.exception.DaoException;
import com.epam.evm.conference.model.User;

import java.sql.Connection;
import java.util.Optional;

public class UserPersistentDaoImpl extends AbstractPersistentDao<User> implements UserPersistentDao {

    private final static String FIND_BY_LOGIN_AND_PASSWORD = "SELECT * FROM user WHERE login = ? AND password = SHA1( ? )";
    private final static String SELECT_ALL = "SELECT * FROM user";
    private final static String TABLE = "user";

    private final static RowMapper<User> MAPPER = new UserRowMapper();
    private final static FieldExtractor<User> EXTRACTOR = new UserFieldExtractor();

    public UserPersistentDaoImpl(Connection connection) {
        super(connection, MAPPER, EXTRACTOR, TABLE, SELECT_ALL);
    }

    @Override
    public Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException {
        return executeForSingleResult(FIND_BY_LOGIN_AND_PASSWORD, login, password);
    }
}
