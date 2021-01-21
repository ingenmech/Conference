package com.epam.evm.conference.dao.helper;

import com.epam.evm.conference.connection.ConnectionPool;
import com.epam.evm.conference.connection.ProxyConnection;
import com.epam.evm.conference.dao.*;
import com.epam.evm.conference.dao.daoInterface.*;
import com.epam.evm.conference.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class DaoHelper implements AutoCloseable {

    private final static Logger LOGGER = LogManager.getLogger(DaoHelper.class);
    private final ProxyConnection connection;

    public DaoHelper(ConnectionPool pool) {
        this.connection = pool.getConnection();
    }

    public UserPersistentDao createUserPersistentDao() {
        return new UserPersistentDaoImpl(connection);
    }

    public ConferencePersistentDao createConferencePersistentDao() {
        return new ConferencePersistentDaoImpl(connection);
    }

    public SectionPersistentDao createSectionPersistentDao() {
        return new SectionPersistentDaoImpl(connection);
    }

    public RequestPersistentDao createRequestPersistentDao() {
        return new RequestPersistentDaoImpl(connection);
    }

    public RequestDtoDao createRequestDtoDao() {
        return new RequestDtoDaoImpl(connection);
    }

    public MessagePersistentDao createMessagePersistentDao() {
        return new MessagePersistentDaoImpl(connection);
    }

    public MessageDtoDao createMessageDtoDao() {
        return new MessageDtoDaoImpl(connection);
    }

    public QuestionPersistentDao createQuestionPersistentDao() {
        return new QuestionPersistentDaoImpl(connection);
    }

    public QuestionDtoDao createQuestionDtoDao() {
        return new QuestionDtoDaoImpl(connection);
    }

    @Override
    public void close() {
        connection.close();
    }

    public void startTransaction() throws DaoException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DaoException("Transaction error", e);
        }
    }

    public void rollback() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            LOGGER.error("Rollback error", e);
        }
    }

    public void endTransaction() throws DaoException {
        try {
            connection.commit();
        } catch (SQLException e) {
            throw new DaoException("Transaction error", e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException throwables) {
                LOGGER.error("Rollback error", throwables);
            }
        }
    }
}
