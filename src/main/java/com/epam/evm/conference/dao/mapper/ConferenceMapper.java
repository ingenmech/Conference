package com.epam.evm.conference.dao.mapper;

import com.epam.evm.conference.exception.DaoException;
import com.epam.evm.conference.model.Conference;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class ConferenceMapper implements RowMapper<Conference>{

    private final static String ID = "id";
    private final static String NAME = "name";
    private final static String DATE = "date";
    private final static String SECTION = "section";

    @Override
    public Conference map(ResultSet resultSet) throws DaoException {

        try {
            Long id = resultSet.getLong(ID);
            String name = resultSet.getString(NAME);

            Timestamp date = resultSet.getTimestamp(DATE);
            LocalDateTime dateTime = date.toLocalDateTime();

            return new Conference(id, name, dateTime);
        } catch (SQLException e) {
            throw new DaoException("Error ConferenceRowMapper", e);
        }
    }
}
