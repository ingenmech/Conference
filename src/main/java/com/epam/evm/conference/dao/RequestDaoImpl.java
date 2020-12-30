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
            "INSERT INTO request(user_id, section_id, topic, status) VALUES (?, ?, ?, ?)";

    private final static String UPDATE_REQUEST =
            "UPDATE request SET user_id = ?, section_id = ?, topic = ?, status = ? WHERE id = ?";
    private final static String SELECT_ALL_REQUESTS_WITH_JOIN_PARAMS =
            "SELECT request.id, request.user_id, request.section_id, request.topic, request.status, section.name AS section_name, user.login AS user_login, conference.name AS conference_name FROM (((request " +
                    "LEFT JOIN user ON request.user_id = user.id) " +
                    "LEFT JOIN section ON request.section_id = section.id) " +
                    "LEFT JOIN conference ON section.conference_id = conference.id) ORDER BY id DESC";
    private final static String SELECT_REQUEST_BY_USER_ID =
            "SELECT request.id, request.user_id, request.section_id, request.topic, request.status, section.name AS section_name, user.login AS user_login, conference.name AS conference_name FROM (((request " +
            "LEFT JOIN section ON request.section_id = section.id) " +
            "LEFT JOIN user ON request.user_id = user.id) " +
            "LEFT JOIN conference ON section.conference_id = conference.id) WHERE user_id = ?";

    private final static String TABLE = "request";
    private final static FieldExtractor<Request> EXTRACTOR = new RequestFieldExtractor();
    private final static RowMapper<Request> MAPPER = new RequestRowMapper();

    public RequestDaoImpl(Connection connection) {
        super(connection, MAPPER, EXTRACTOR, TABLE, INSERT_REQUEST, UPDATE_REQUEST, SELECT_ALL_REQUESTS_WITH_JOIN_PARAMS);
    }

    @Override
    public List<Request> findAllRequestsByUserId(Long userId) throws DaoException {
        return executeQuery(SELECT_REQUEST_BY_USER_ID, userId);
    }

//    @Override
//    public List<Request> findAllRequestsWithParams() throws DaoException {
//        return executeQuery(SELECT_REQUESTS_WITH_JOIN_PARAMS);
//    }


}
