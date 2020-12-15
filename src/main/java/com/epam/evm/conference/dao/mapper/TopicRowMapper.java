package com.epam.evm.conference.dao.mapper;

import com.epam.evm.conference.exception.DaoException;
import com.epam.evm.conference.model.Topic;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TopicRowMapper implements RowMapper {

    private final static String ID = "id";
    private final static String USER_ID = "user_id";
    private final static String SECTION_ID = "section_id";
    private final static String NAME = "name";
    private final static String STATUS = "status";

    @Override
    public Object map(ResultSet resultSet) throws DaoException {
        try {
            Long id = resultSet.getLong(ID);
            Long userId = resultSet.getLong(USER_ID);
            Long sectionId = resultSet.getLong(SECTION_ID);
            String name = resultSet.getString(NAME);
            String status = resultSet.getString(STATUS);

            return new Topic(id, sectionId, userId, name, status);
        } catch (SQLException e) {
            throw new DaoException("Error SectionRowMapper", e);
        }
    }
}
