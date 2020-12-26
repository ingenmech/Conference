package com.epam.evm.conference.dao;

import com.epam.evm.conference.dao.daoInterface.ConferenceDao;
import com.epam.evm.conference.dao.extractor.ConferenceFieldExtractor;
import com.epam.evm.conference.dao.extractor.FieldExtractor;
import com.epam.evm.conference.dao.mapper.ConferenceRowMapper;
import com.epam.evm.conference.dao.mapper.RowMapper;
import com.epam.evm.conference.model.Conference;

import java.sql.Connection;

public class ConferenceDaoImpl extends AbstractDao<Conference> implements ConferenceDao {

    private final static String INSERT_CONFERENCE = "INSERT INTO conference(name, date) VALUES (?, ?)";
    private final static String UPDATE_CONFERENCE = "";
    private final static String SELECT_ALL = "SELECT * FROM conference";

    private final static String TABLE = "conference";
    private final static FieldExtractor<Conference> EXTRACTOR = new ConferenceFieldExtractor();
    private final static RowMapper<Conference> MAPPER = new ConferenceRowMapper();

    public ConferenceDaoImpl(Connection connection) {
        super(connection, MAPPER, EXTRACTOR, TABLE, INSERT_CONFERENCE, UPDATE_CONFERENCE, SELECT_ALL);
    }

}
