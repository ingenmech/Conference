package com.epam.evm.conference.dao;

import com.epam.evm.conference.dao.daoInterface.SectionPersistentDao;
import com.epam.evm.conference.dao.extractor.FieldExtractor;
import com.epam.evm.conference.dao.extractor.SectionFieldExtractor;
import com.epam.evm.conference.dao.mapper.RowMapper;
import com.epam.evm.conference.dao.mapper.SectionRowMapper;
import com.epam.evm.conference.exception.DaoException;
import com.epam.evm.conference.model.Section;

import java.sql.Connection;
import java.util.List;

public class SectionPersistentDaoImpl extends AbstractPersistentDao<Section> implements SectionPersistentDao {

    private final static String SELECT_ALL = "SELECT * FROM section";
    private final static String SELECT_SECTIONS_BY_CONFERENCE_ID= "SELECT * FROM section WHERE conference_id = ? AND NOT status='DEPRECATED'";
    private final static String SELECT_ACTUAL_SECTIONS= "SELECT * FROM section WHERE NOT status='DEPRECATED'";
    private final static String TABLE = "section";
    private final static FieldExtractor<Section> EXTRACTOR = new SectionFieldExtractor();
    private final static RowMapper<Section> MAPPER = new SectionRowMapper();

    public SectionPersistentDaoImpl(Connection connection) {
        super(connection, MAPPER, EXTRACTOR,TABLE, SELECT_ALL);
    }


    @Override
    public List<Section> findSectionsByConferenceId(Long conferenceId) throws DaoException {
        return executeQuery(SELECT_SECTIONS_BY_CONFERENCE_ID, conferenceId);
    }

    @Override
    public List<Section> findActualSections() throws DaoException {
        return executeQuery(SELECT_ACTUAL_SECTIONS);
    }
}
