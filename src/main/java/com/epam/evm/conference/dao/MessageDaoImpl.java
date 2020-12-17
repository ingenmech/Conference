package com.epam.evm.conference.dao;

import com.epam.evm.conference.dao.daoInterface.MessageDao;
import com.epam.evm.conference.dao.extractor.FieldExtractor;
import com.epam.evm.conference.dao.extractor.MessageFieldExtractor;
import com.epam.evm.conference.dao.mapper.MessageRowMapper;
import com.epam.evm.conference.dao.mapper.RowMapper;
import com.epam.evm.conference.model.Message;

import java.sql.Connection;

public class MessageDaoImpl extends AbstractDao<Message> implements MessageDao {

    private final static String INSERT_MESSAGE =
            "INSERT INTO message(QUESTION_ID, USER_ID, DATE_TIME, CONTENT) VALUES (?, ?, ?, ?)";

    private final static String UPDATE_MESSAGE = "";
    private final static String TABLE = "message";
    private final static RowMapper<Message> MAPPER = new MessageRowMapper();
    private final static FieldExtractor<Message> EXTRACTOR = new MessageFieldExtractor();

    public MessageDaoImpl(Connection connection) {
        super(connection, MAPPER, EXTRACTOR, TABLE, INSERT_MESSAGE, UPDATE_MESSAGE);
    }
}
