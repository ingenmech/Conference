package com.epam.evm.conference.dao.mapper;

import com.epam.evm.conference.exception.DaoException;
import com.epam.evm.conference.model.Request;
import com.epam.evm.conference.model.RequestStatus;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RequestRowMapper implements RowMapper<Request> {

    private final static String ID = "id";
    private final static String USER_ID = "user_id";
    private final static String SECTION_ID = "section_id";
    private final static String TOPIC = "topic";
    private final static String STATUS = "status";

    private final static String SECTION_NAME = "section_name";
    private final static String CONFERENCE_NAME = "conference_name";
    private final static String USER_LOGIN = "user_login";


    @Override
    public Request map(ResultSet resultSet) throws DaoException {
        try {
            Long id = resultSet.getLong(ID);
            Long userId = resultSet.getLong(USER_ID);
            Long sectionId = resultSet.getLong(SECTION_ID);
            String topic = resultSet.getString(TOPIC);
            String statusRow = resultSet.getString(STATUS);
            RequestStatus status =RequestStatus.valueOf(statusRow);

            String sectionName = resultSet.getString(SECTION_NAME);
            String conferenceName = resultSet.getString(CONFERENCE_NAME);
            String userLogin= resultSet.getString(USER_LOGIN);

            return new Request(id, sectionId, userId, topic, status, conferenceName, sectionName, userLogin);
        } catch (SQLException e) {
            throw new DaoException("Error SectionRowMapper", e);
        }
    }
}
