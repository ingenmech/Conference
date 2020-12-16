package com.epam.evm.conference.dao;

import com.epam.evm.conference.dao.extractor.FieldExtractor;
import com.epam.evm.conference.dao.extractor.TopicFieldExtractor;
import com.epam.evm.conference.dao.mapper.RowMapper;
import com.epam.evm.conference.dao.mapper.TopicRowMapper;
import com.epam.evm.conference.exception.DaoException;
import com.epam.evm.conference.model.Topic;
import com.epam.evm.conference.model.User;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class TopicDaoImpl extends AbstractDao<Topic> implements TopicDao{

    private final static String INSERT_TOPIC = "INSERT INTO topic(ID, USER_ID, SECTION_ID, NAME, STATUS) " +
            "VALUES (?, ?, ?, ?, ?)";
    private final static String UPDATE_TOPIC_STATUS = "UPDATE topic SET status = ? WHERE id = ?";
    private final static String SELECT_TOPIC_BY_USER_ID = "SELECT * FROM topic WHERE user_id = ?";
    private final static String TABLE = "topic";

    private final static FieldExtractor EXTRACTOR = new TopicFieldExtractor();
    private final static RowMapper MAPPER = new TopicRowMapper();

    public TopicDaoImpl(Connection connection) {
        super(connection, MAPPER, EXTRACTOR, TABLE, INSERT_TOPIC, UPDATE_TOPIC_STATUS);
    }

    @Override
    public List<Topic> findAllTopicsByUserId(Long userId) throws DaoException {

        return executeQuery(SELECT_TOPIC_BY_USER_ID, userId);
    }
}
