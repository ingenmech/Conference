package com.epam.evm.conference.dao.mapper;

import com.epam.evm.conference.exception.DaoException;
import com.epam.evm.conference.model.Question;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionRowMapper implements RowMapper<Question> {

    private final static String ID = "id";
    private final static String USER_ID = "user_id";
    private final static String CONTENT = "content";

    private final static String USER_LOGIN = "user_login";

    @Override
    public Question map(ResultSet resultSet) throws DaoException {

        try {
            Long id = resultSet.getLong(ID);
            Long userId = resultSet.getLong(USER_ID);
            String content = resultSet.getString(CONTENT);
            String userLogin = resultSet.getString(USER_LOGIN);
            return new Question(id, userId, content, userLogin);
        } catch (SQLException e) {
            throw new DaoException("Question RowMapper", e);
        }
    }
}
