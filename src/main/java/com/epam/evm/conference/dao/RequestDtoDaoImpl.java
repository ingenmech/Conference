package com.epam.evm.conference.dao;

import com.epam.evm.conference.dao.daoInterface.RequestDtoDao;
import com.epam.evm.conference.dao.mapper.RequestDtoRowMapper;
import com.epam.evm.conference.dao.mapper.RowMapper;
import com.epam.evm.conference.exception.DaoException;
import com.epam.evm.conference.model.RequestDto;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class RequestDtoDaoImpl extends AbstractDao<RequestDto> implements RequestDtoDao {

    private final static String SELECT_ALL_REQUESTS =
            "SELECT request.id, request.user_id, request.section_id, request.topic, request.status, " +
                    "section.name AS section_name, section.status AS section_status, user.login AS user_login," +
                    " conference.name AS conference_name, conference.date AS conference_date FROM (((request " +
                    "LEFT JOIN user ON request.user_id = user.id) " +
                    "LEFT JOIN section ON request.section_id = section.id) " +
                    "LEFT JOIN conference ON section.conference_id = conference.id)" +
                    " ORDER BY id DESC";
    private final static String SELECT_ACTUAL_REQUESTS =
            "SELECT request.id, request.user_id, request.section_id, request.topic, request.status, " +
                    "section.name AS section_name, section.status AS section_status, user.login AS user_login," +
                    " conference.name AS conference_name, conference.date AS conference_date FROM (((request " +
                    "LEFT JOIN user ON request.user_id = user.id) " +
                    "LEFT JOIN section ON request.section_id = section.id) " +
                    "LEFT JOIN conference ON section.conference_id = conference.id) " +
                    "WHERE conference.date > ? ORDER BY id DESC  LIMIT ? OFFSET ?";
    private final static String SELECT_REQUEST_BY_USER_ID =
            "SELECT request.id, request.user_id, request.section_id, request.topic, request.status, " +
                    "section.name AS section_name, section.status AS section_status, user.login AS user_login," +
                    " conference.name AS conference_name, conference.date AS conference_date FROM (((request " +
                    "LEFT JOIN section ON request.section_id = section.id) " +
                    "LEFT JOIN user ON request.user_id = user.id) " +
                    "LEFT JOIN conference ON section.conference_id = conference.id) " +
                    "WHERE user_id = ? ORDER BY id DESC";
    private final static String TABLE = "request";
    private final static RowMapper<RequestDto> MAPPER = new RequestDtoRowMapper();

    public RequestDtoDaoImpl(Connection connection) {
        super(connection, MAPPER, TABLE, SELECT_ALL_REQUESTS);
    }

    @Override
    public List<RequestDto> findAllRequestsByUserId(Long userId) throws DaoException {
        return executeQuery(SELECT_REQUEST_BY_USER_ID, userId);
    }

    @Override
    public List<RequestDto> findActualRequests(LocalDateTime actualDate, int limit, int offset) throws DaoException {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        String dateTime = actualDate.format(formatter);
        return executeQuery(SELECT_ACTUAL_REQUESTS, dateTime, limit, offset);
    }
}
