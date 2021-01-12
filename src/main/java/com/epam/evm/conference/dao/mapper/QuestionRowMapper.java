package com.epam.evm.conference.dao.mapper;

import com.epam.evm.conference.exception.DaoException;
import com.epam.evm.conference.model.Question;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionRowMapper implements RowMapper<Question> {

    private final static String ID = "id";
    private final static String USER_ID = "user_id";
    private final static String CONFERENCE_ID = "conference_id";
    private final static String CONTENT = "content";
    private final static String USER_LOGIN = "user_login";
    private final static String CONFERENCE_NAME = "conference_name";

    @Override
    public Question map(ResultSet resultSet) throws DaoException {

        try {
            Long id = resultSet.getLong(ID);
            Long userId = resultSet.getLong(USER_ID);
            Long conferenceId = resultSet.getLong(CONFERENCE_ID);
            String content = resultSet.getString(CONTENT);
            String userLogin = resultSet.getString(USER_LOGIN);
            String conferenceName = resultSet.getString(CONFERENCE_NAME);
            return new Question(id, userId, conferenceId, content, userLogin, conferenceName);
        } catch (SQLException e) {
            throw new DaoException("Question RowMapper", e);
        }
    }
}
