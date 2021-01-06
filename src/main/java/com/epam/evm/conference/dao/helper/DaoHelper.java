package com.epam.evm.conference.dao.helper;

import com.epam.evm.conference.connection.ConnectionPool;
import com.epam.evm.conference.connection.ProxyConnection;
import com.epam.evm.conference.dao.*;
import com.epam.evm.conference.dao.daoInterface.*;
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

    public SectionDao createSectionDao() {
        return new SectionDaoImpl(connection);
    }

    public RequestDao createTopicDao() {
        return new RequestDaoImpl(connection);
    }

    public MessageDao createMessageDao() {
        return new MessageDaoImpl(connection);
    }

    public QuestionDao createQuestionDao() {
        return new QuestionDaoImpl(connection);
    }

    @Override
    public void close() {
        connection.close();
    }

    public void setAutoCommit(boolean autoCommit) throws DaoException {
        try {
            connection.setAutoCommit(autoCommit);
        } catch (SQLException e) {
            throw new DaoException("Transaction error", e);
        }
    }

    public void rollback() throws DaoException {
        try {
            connection.rollback();
        } catch (SQLException e) {
            throw new DaoException("Rollback error", e);
        }
    }

    public void endTransaction() throws DaoException {
        try {
            connection.commit();
        } catch (SQLException e) {
            rollback();
            throw new DaoException("Transaction error", e);
        }
    }
}
