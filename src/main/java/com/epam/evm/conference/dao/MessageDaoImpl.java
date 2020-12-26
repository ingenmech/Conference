package com.epam.evm.conference.dao;

import com.epam.evm.conference.dao.daoInterface.MessageDao;
import com.epam.evm.conference.dao.extractor.FieldExtractor;
import com.epam.evm.conference.dao.extractor.MessageFieldExtractor;
import com.epam.evm.conference.dao.mapper.MessageRowMapper;
import com.epam.evm.conference.dao.mapper.RowMapper;
import com.epam.evm.conference.exception.DaoException;
import com.epam.evm.conference.model.Message;

import java.sql.Connection;
import java.util.List;

public class MessageDaoImpl extends AbstractDao<Message> implements MessageDao {

    private final static String INSERT_MESSAGE =
            "INSERT INTO message(question_id, user_id, date_time, content) VALUES (?, ?, ?, ?)";
    private final static String SELECT_MESSAGE_BY_QUESTION_ID =
            "SELECT message.id, message.question_id, message.user_id, message.date_time, user.login AS user_login, message.content FROM message " +
                    "LEFT JOIN user ON message.user_id = user.id " +
                    "WHERE question_id = ? ORDER BY date_time";
    private final static String SELECT_ALL_MESSAGE_WITH_LOGIN =
            "SELECT message.id, message.question_id, message.user_id, message.date_time, user.login AS user_login, message.content FROM message " +
                    "LEFT JOIN user ON message.user_id = user.id";
    private final static String UPDATE_MESSAGE = "";
    private final static String TABLE = "message";
    private final static RowMapper<Message> MAPPER = new MessageRowMapper();
    private final static FieldExtractor<Message> EXTRACTOR = new MessageFieldExtractor();

    public MessageDaoImpl(Connection connection) {
        super(connection, MAPPER, EXTRACTOR, TABLE, INSERT_MESSAGE, UPDATE_MESSAGE, SELECT_ALL_MESSAGE_WITH_LOGIN);
    }

    @Override
    public List<Message> findMessagesByQuestionId(Long questionId) throws DaoException {
        return executeQuery(SELECT_MESSAGE_BY_QUESTION_ID, questionId);
    }
}
