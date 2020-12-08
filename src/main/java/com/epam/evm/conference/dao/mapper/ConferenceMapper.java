package com.epam.evm.conference.dao.mapper;

import com.epam.evm.conference.exception.DaoException;
import com.epam.evm.conference.model.Conference;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ConferenceMapper implements RowMapper<Conference>{

    private final static String ID = "ID";
    private final static String NAME = "NAME";
    private final static String DATE = "DATE";
    private final static String SECTION = "SECTION";

    @Override
    public Conference map(ResultSet resultSet) throws DaoException {

        try {
            String idValue = resultSet.getString(ID);
            Long id = Long.parseLong(idValue);
            String name = resultSet.getString(NAME);

            String date = resultSet.getString(DATE);
            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
            LocalDateTime dateTime = LocalDateTime.parse(date, formatter);

            //String section = resultSet.getString(SECTION);
            return new Conference(id, name, dateTime, null);
        } catch (SQLException e) {
            throw new DaoException("Error ConferenceRowMapper", e);
        }
    }
}
