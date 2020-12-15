package com.epam.evm.conference.dao;

import com.epam.evm.conference.dao.extractor.ConferenceFieldExtractor;
import com.epam.evm.conference.dao.extractor.FieldExtractor;
import com.epam.evm.conference.dao.mapper.ConferenceMapper;
import com.epam.evm.conference.dao.mapper.RowMapper;
import com.epam.evm.conference.model.Conference;

import java.sql.Connection;

public class ConferenceDaoImpl extends AbstractDao<Conference> implements ConferenceDao {

    private final static String INSERT_CONFERENCE = "INSERT INTO conference(NAME, DATE) VALUES (?, ?)";

    private final static String TABLE = "conference";
    private final static FieldExtractor EXTRACTOR = new ConferenceFieldExtractor();
    private final static RowMapper<Conference> MAPPER = new ConferenceMapper();

    public ConferenceDaoImpl(Connection connection) {
        super(connection, MAPPER, EXTRACTOR, TABLE, INSERT_CONFERENCE);
    }

}
