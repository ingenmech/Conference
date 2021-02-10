package com.epam.evm.conference.dao.mapper;

import com.epam.evm.conference.exception.DaoException;
import com.epam.evm.conference.model.Section;
import com.epam.evm.conference.model.SectionStatus;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SectionRowMapper implements RowMapper<Section> {

    private final static String ID = "id";
    private final static String CONFERENCE_ID = "conference_id";
    private final static String NAME = "name";
    private final static String STATUS = "status";

    @Override
    public Section map(ResultSet resultSet) throws DaoException {

        try {
            Long id = resultSet.getLong(ID);
            Long conferenceId = resultSet.getLong(CONFERENCE_ID);
            String name = resultSet.getString(NAME);
            String status = resultSet.getString(STATUS);

            return new Section(id, conferenceId, name, SectionStatus.valueOf(status));
        } catch (SQLException e) {
            throw new DaoException("Error SectionRowMapper", e);
        }
    }
}
