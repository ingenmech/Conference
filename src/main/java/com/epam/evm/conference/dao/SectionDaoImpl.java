package com.epam.evm.conference.dao;

import com.epam.evm.conference.dao.daoInterface.SectionDao;
import com.epam.evm.conference.dao.extractor.FieldExtractor;
import com.epam.evm.conference.dao.extractor.SectionFieldExtractor;
import com.epam.evm.conference.dao.mapper.RowMapper;
import com.epam.evm.conference.dao.mapper.SectionRowMapper;
import com.epam.evm.conference.model.Section;

import java.sql.Connection;

public class SectionDaoImpl extends AbstractDao<Section> implements SectionDao {

    private final static String INSERT_SECTION = "INSERT INTO section(CONFERENCE_ID, NAME) VALUES (?, ?)";
    private final static String UPDATE_SECTION = "";

    private final static String TABLE = "section";
    private final static FieldExtractor<Section> EXTRACTOR = new SectionFieldExtractor();
    private final static RowMapper<Section> MAPPER = new SectionRowMapper();

    public SectionDaoImpl(Connection connection) {
        super(connection, MAPPER, EXTRACTOR, TABLE, INSERT_SECTION, UPDATE_SECTION);
    }


}
