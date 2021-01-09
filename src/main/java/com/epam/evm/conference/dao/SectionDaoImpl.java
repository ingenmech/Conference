package com.epam.evm.conference.dao;

import com.epam.evm.conference.dao.daoInterface.SectionDao;
import com.epam.evm.conference.dao.extractor.FieldExtractor;
import com.epam.evm.conference.dao.extractor.SectionFieldExtractor;
import com.epam.evm.conference.dao.mapper.RowMapper;
import com.epam.evm.conference.dao.mapper.SectionRowMapper;
import com.epam.evm.conference.exception.DaoException;
import com.epam.evm.conference.model.Section;

import java.sql.Connection;
import java.util.List;

public class SectionDaoImpl extends AbstractDao<Section> implements SectionDao {

    private final static String SELECT_ALL = "SELECT * FROM section";
    private final static String SELECT_SECTIONS_BY_CONFERENCE_ID= "SELECT * FROM section WHERE conference_id = ?";

    private final static String TABLE = "section";
    private final static FieldExtractor<Section> EXTRACTOR = new SectionFieldExtractor();
    private final static RowMapper<Section> MAPPER = new SectionRowMapper();

    public SectionDaoImpl(Connection connection) {
        super(connection, MAPPER, EXTRACTOR,TABLE, SELECT_ALL);
    }


    @Override
    public List<Section> findSectionsByConferenceId(Long conferenceId) throws DaoException {
        return executeQuery(SELECT_SECTIONS_BY_CONFERENCE_ID, conferenceId);
    }
}
