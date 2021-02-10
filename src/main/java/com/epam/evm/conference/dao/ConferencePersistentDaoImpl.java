package com.epam.evm.conference.dao;

import com.epam.evm.conference.dao.daoInterface.ConferencePersistentDao;
import com.epam.evm.conference.dao.extractor.ConferenceFieldExtractor;
import com.epam.evm.conference.dao.extractor.FieldExtractor;
import com.epam.evm.conference.dao.mapper.ConferenceRowMapper;
import com.epam.evm.conference.dao.mapper.RowMapper;
import com.epam.evm.conference.exception.DaoException;
import com.epam.evm.conference.model.Conference;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ConferencePersistentDaoImpl extends AbstractPersistentDao<Conference> implements ConferencePersistentDao {

    private final static String SELECT_ALL = "SELECT * FROM conference ORDER BY date DESC";
    private final static String SELECT_ACTUAL_CONFERENCES = "SELECT * FROM conference WHERE date > ? ORDER BY date";
    private final static String COUNT_ACTUAL_CONFERENCES = "SELECT COUNT(*) AS rows_number FROM conference WHERE date > ?";
    private final static String TABLE = "conference";
    private final static FieldExtractor<Conference> EXTRACTOR = new ConferenceFieldExtractor();
    private final static RowMapper<Conference> MAPPER = new ConferenceRowMapper();

    public ConferencePersistentDaoImpl(Connection connection) {
        super(connection, MAPPER, EXTRACTOR, TABLE, SELECT_ALL);
    }

    @Override
    public List<Conference> findActualConferences(LocalDateTime actualDate) throws DaoException {
        return executeQuery(SELECT_ACTUAL_CONFERENCES, actualDate);
    }

    @Override
    public List<Conference> findActualConferencesForPagination(LocalDateTime actualDate, int limit, int offset) throws DaoException {
        String dateTime = convertDate(actualDate);
        String query = SELECT_ACTUAL_CONFERENCES.concat(" LIMIT ? OFFSET ?");
        return executeQuery(query, dateTime, limit, offset);
    }

    @Override
    public Long countActualConference(LocalDateTime actualDate) throws DaoException {
        String dateTime = convertDate(actualDate);
        return executeQueryCounter(COUNT_ACTUAL_CONFERENCES, dateTime);
    }

    private String convertDate(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        return date.format(formatter);
    }
}
