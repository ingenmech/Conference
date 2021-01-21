package com.epam.evm.conference.dao.mapper;

import com.epam.evm.conference.exception.DaoException;
import com.epam.evm.conference.model.Message;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MessageRowMapper implements RowMapper<Message> {

    private final static String ID = "id";
    private final static String QUESTION_ID = "question_id";
    private final static String USER_ID = "user_id";
    private final static String CONTENT = "content";

    @Override
    public Message map(ResultSet resultSet) throws DaoException {

        try {
            Long id = resultSet.getLong(ID);
            Long questionId = resultSet.getLong(QUESTION_ID);
            Long userId = resultSet.getLong(USER_ID);
            String content = resultSet.getString(CONTENT);
            return new Message(id, questionId, userId, content);
        } catch (SQLException e) {
            throw new DaoException("Message RowMapper error", e);
        }
    }
}
