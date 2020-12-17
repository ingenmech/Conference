package com.epam.evm.conference.dao.mapper;

import com.epam.evm.conference.exception.DaoException;
import com.epam.evm.conference.model.Question;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionRowMapper implements RowMapper<Question> {

    private final static String ID = "id";
    private final static String USER_ID = "user_id";
    private final static String CONTENT = "content";

    @Override
    public Question map(ResultSet resultSet) throws DaoException {

        try {
            Long id = resultSet.getLong(ID);
            Long userId = resultSet.getLong(USER_ID);
            String content = resultSet.getString(CONTENT);
            return new Question(id, userId, content);
        } catch (SQLException e) {
            throw new DaoException("Question RowMapper", e);
        }
    }
}
