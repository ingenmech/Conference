package com.epam.evm.conference.dao.helper;

import com.epam.evm.conference.connection.ConnectionPool;
import com.epam.evm.conference.connection.ProxyConnection;
import com.epam.evm.conference.dao.ConferenceDao;
import com.epam.evm.conference.dao.ConferenceDaoImpl;
import com.epam.evm.conference.dao.UserDao;
import com.epam.evm.conference.dao.UserDaoImpl;
import com.epam.evm.conference.exception.DaoException;

import java.sql.SQLException;

public class DaoHelper implements AutoCloseable {

    private ProxyConnection connection;

    public DaoHelper(ConnectionPool pool) {
        this.connection = pool.getConnection();
    }

    public UserDao createUserDao() {
        return new UserDaoImpl(connection);
    }

    public ConferenceDao createConferenceDao() {
        return new ConferenceDaoImpl(connection);
    }

    @Override
    public void close() {
        connection.close();
    }

    public void startTransaction() throws DaoException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            rollback();
            throw new DaoException("Transaction error", e);
        }
    }

    private void rollback() throws DaoException {
        try {
            connection.rollback();
        } catch (SQLException stack) {
            throw new DaoException("Rollback error", stack);
        }
    }

    public void endTransaction() throws DaoException {
        try {
            connection.commit();
        } catch (SQLException e) {
            throw new DaoException("Transaction error", e);
        }
    }
}
