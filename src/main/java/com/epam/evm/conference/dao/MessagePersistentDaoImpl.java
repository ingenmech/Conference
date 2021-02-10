package com.epam.evm.conference.dao;

import com.epam.evm.conference.dao.daoInterface.MessagePersistentDao;
import com.epam.evm.conference.dao.extractor.FieldExtractor;
import com.epam.evm.conference.dao.extractor.MessageFieldExtractor;
import com.epam.evm.conference.dao.mapper.MessageRowMapper;
import com.epam.evm.conference.dao.mapper.RowMapper;
import com.epam.evm.conference.model.Message;

import java.sql.Connection;

public class MessagePersistentDaoImpl extends AbstractPersistentDao<Message> implements MessagePersistentDao {

    private final static String SELECT_ALL_MESSAGE = "SELECT * FROM question ORDER BY id";
    private final static String TABLE = "message";
    private final static RowMapper<Message> MAPPER = new MessageRowMapper();
    private final static FieldExtractor<Message> EXTRACTOR = new MessageFieldExtractor();

    public MessagePersistentDaoImpl(Connection connection) {
        super(connection, MAPPER, EXTRACTOR, TABLE, SELECT_ALL_MESSAGE);
    }
}
