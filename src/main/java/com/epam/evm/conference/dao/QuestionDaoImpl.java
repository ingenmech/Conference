package com.epam.evm.conference.dao;

import com.epam.evm.conference.dao.daoInterface.QuestionDao;
import com.epam.evm.conference.dao.extractor.FieldExtractor;
import com.epam.evm.conference.dao.extractor.QuestionFieldExtractor;
import com.epam.evm.conference.dao.mapper.QuestionRowMapper;
import com.epam.evm.conference.dao.mapper.RowMapper;
import com.epam.evm.conference.model.Question;

import java.sql.Connection;

public class QuestionDaoImpl extends AbstractDao<Question> implements QuestionDao {

    private final static String INSERT_QUESTION = "INSERT INTO question(USER_ID, CONTENT) VALUES (?, ?)";
    private final static String UPDATE_QUESTION = "";
    private final static String TABLE = "question";

    private final static RowMapper<Question> MAPPER = new QuestionRowMapper();
    private final static FieldExtractor<Question> EXTRACTOR = new QuestionFieldExtractor();

    public QuestionDaoImpl(Connection connection) {
        super(connection, MAPPER, EXTRACTOR, TABLE, INSERT_QUESTION, UPDATE_QUESTION);
    }
}
