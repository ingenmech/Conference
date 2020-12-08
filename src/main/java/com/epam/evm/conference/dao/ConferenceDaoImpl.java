package com.epam.evm.conference.dao;

import com.epam.evm.conference.dao.extractor.ConferenceFieldExtractor;
import com.epam.evm.conference.dao.mapper.ConferenceMapper;
import com.epam.evm.conference.dao.mapper.RowMapper;
import com.epam.evm.conference.exception.DaoException;
import com.epam.evm.conference.model.Conference;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConferenceDaoImpl extends AbstractDao<Conference> implements ConferenceDao {

    private final static String SAVE_CONFERENCE = "INSERT INTO conference(NAME, DATE) VALUES (?, ?)";
    private final static String CONFERENCE_ID = "SET @last_conf_id=LAST_INSERT_ID()";
    private final static String SAVE_SECTION = "INSERT INTO section(CONFERENCE_ID, NAME) VALUES (@last_conf_id, ?)";
    private final static String SECTION = ", (@last_conf_id, ?)";

    private final static String TABLE = "conference";
    private final static ConferenceFieldExtractor EXTRACTOR = new ConferenceFieldExtractor();
    private final static RowMapper<Conference> MAPPER = new ConferenceMapper();

    public ConferenceDaoImpl(Connection connection) {
        super(connection, MAPPER, EXTRACTOR, TABLE);
    }

    @Override
    public void save(Conference conference) throws DaoException {

        Map<Integer, Object> conferenceFields = EXTRACTOR.extract(conference);

        List<String> sections = conference.getSections();
        Map<Integer, Object> sectionFields = new HashMap<>();
        StringBuilder builder = new StringBuilder(SAVE_SECTION);
        for (int i = 1; i <= sections.size(); i++) {
            sectionFields.put(i, sections.get(i - 1));
            if (i >= 2) {
                builder.append(SECTION);
            }
        }
        String sectionQuery = builder.toString();

        createPreparedStatement(SAVE_CONFERENCE, conferenceFields);
        createStatementForSingleQuery(CONFERENCE_ID);
        createPreparedStatement(sectionQuery, sectionFields);
    }
}
