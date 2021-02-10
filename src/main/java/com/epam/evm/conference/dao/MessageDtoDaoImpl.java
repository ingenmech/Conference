package com.epam.evm.conference.dao;

import com.epam.evm.conference.dao.daoInterface.MessageDtoDao;
import com.epam.evm.conference.dao.mapper.MessageDtoRowMapper;
import com.epam.evm.conference.dao.mapper.RowMapper;
import com.epam.evm.conference.exception.DaoException;
import com.epam.evm.conference.model.MessageDto;

import java.sql.Connection;
import java.util.List;

public class MessageDtoDaoImpl extends AbstractDao<MessageDto> implements MessageDtoDao {

    private final static String SELECT_MESSAGE_BY_QUESTION_ID =
            "SELECT message.id, message.content, user.login AS user_login FROM message LEFT JOIN user ON message.user_id = user.id WHERE question_id = ? ORDER BY id";
    private final static String SELECT_ALL_MESSAGES_WITH_LOGIN =
            "SELECT message.id, message.content, user.login AS user_login FROM message LEFT JOIN user ON message.user_id = user.id ORDER BY id";
    private final static String TABLE = "message";
    private final static RowMapper<MessageDto> MAPPER = new MessageDtoRowMapper();

    public MessageDtoDaoImpl(Connection connection) {
        super(connection, MAPPER, TABLE, SELECT_ALL_MESSAGES_WITH_LOGIN);
    }

    @Override
    public List<MessageDto> findMessagesByQuestionId(Long questionId) throws DaoException {
        return executeQuery(SELECT_MESSAGE_BY_QUESTION_ID, questionId);
    }
}
