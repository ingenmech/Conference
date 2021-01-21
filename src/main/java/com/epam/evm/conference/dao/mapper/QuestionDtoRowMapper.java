package com.epam.evm.conference.dao.mapper;

import com.epam.evm.conference.exception.DaoException;
import com.epam.evm.conference.model.QuestionDto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionDtoRowMapper implements RowMapper<QuestionDto> {

    private final static String ID = "id";
    private final static String CONTENT = "content";
    private final static String USER_LOGIN = "user_login";
    private final static String CONFERENCE_NAME = "conference_name";

    @Override
    public QuestionDto map(ResultSet resultSet) throws DaoException {

        try {
            Long id = resultSet.getLong(ID);
            String content = resultSet.getString(CONTENT);
            String userLogin = resultSet.getString(USER_LOGIN);
            String conferenceName = resultSet.getString(CONFERENCE_NAME);
            return new QuestionDto(id, content, userLogin, conferenceName);
        } catch (SQLException e) {
            throw new DaoException("Question RowMapper", e);
        }
    }
}
