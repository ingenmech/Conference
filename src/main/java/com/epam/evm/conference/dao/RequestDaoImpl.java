package com.epam.evm.conference.dao;

import com.epam.evm.conference.dao.daoInterface.RequestDao;
import com.epam.evm.conference.dao.extractor.FieldExtractor;
import com.epam.evm.conference.dao.extractor.RequestFieldExtractor;
import com.epam.evm.conference.dao.mapper.RowMapper;
import com.epam.evm.conference.dao.mapper.RequestRowMapper;
import com.epam.evm.conference.exception.DaoException;
import com.epam.evm.conference.model.Request;

import java.sql.Connection;
import java.util.List;

public class RequestDaoImpl extends AbstractDao<Request> implements RequestDao {

    private final static String INSERT_REQUEST =
            "INSERT INTO request(USER_ID, SECTION_ID, TOPIC, STATUS) VALUES (?, ?, ?, ?)";

    private final static String UPDATE_REQUEST_STATUS =
            "UPDATE request SET user_id = ?, section_id = ?, topic = ?, status = ? WHERE id = ?";
    private final static String SELECT_REQUEST_BY_USER_ID = "SELECT * FROM request WHERE user_id = ?";
    private final static String TABLE = "request";

    private final static FieldExtractor<Request> EXTRACTOR = new RequestFieldExtractor();
    private final static RowMapper<Request> MAPPER = new RequestRowMapper();

    public RequestDaoImpl(Connection connection) {
        super(connection, MAPPER, EXTRACTOR, TABLE, INSERT_REQUEST, UPDATE_REQUEST_STATUS);
    }

    @Override
    public List<Request> findAllRequestsByUserId(Long userId) throws DaoException {

        return executeQuery(SELECT_REQUEST_BY_USER_ID, userId);
    }
}
