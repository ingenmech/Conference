package com.epam.evm.conference.dao.mapper;

import com.epam.evm.conference.exception.DaoException;
import com.epam.evm.conference.model.MessageDto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MessageDtoRowMapper implements RowMapper<MessageDto> {

    private final static String CONTENT = "content";
    private final static String USER_LOGIN = "user_login";

    @Override
    public MessageDto map(ResultSet resultSet) throws DaoException {

        try {
            String content = resultSet.getString(CONTENT);
            String userLogin = resultSet.getString(USER_LOGIN);
            return new MessageDto(content, userLogin);
        } catch (SQLException e) {
            throw new DaoException("Message RowMapper error", e);
        }
    }
}
