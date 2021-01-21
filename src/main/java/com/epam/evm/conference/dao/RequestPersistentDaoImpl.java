package com.epam.evm.conference.dao;

import com.epam.evm.conference.dao.daoInterface.RequestPersistentDao;
import com.epam.evm.conference.dao.extractor.FieldExtractor;
import com.epam.evm.conference.dao.extractor.RequestFieldExtractor;
import com.epam.evm.conference.dao.mapper.RowMapper;
import com.epam.evm.conference.dao.mapper.RequestRowMapper;
import com.epam.evm.conference.model.Request;

import java.sql.Connection;

public class RequestPersistentDaoImpl extends AbstractPersistentDao<Request> implements RequestPersistentDao {

    private final static String SELECT_ALL_REQUESTS_WITH_JOIN_PARAMS = "SELECT * FROM section ORDER BY id DESC";
    private final static String TABLE = "request";
    private final static FieldExtractor<Request> EXTRACTOR = new RequestFieldExtractor();
    private final static RowMapper<Request> MAPPER = new RequestRowMapper();

    public RequestPersistentDaoImpl(Connection connection) {
        super(connection, MAPPER, EXTRACTOR, TABLE, SELECT_ALL_REQUESTS_WITH_JOIN_PARAMS);
    }

}
