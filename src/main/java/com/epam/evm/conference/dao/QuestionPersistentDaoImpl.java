package com.epam.evm.conference.dao;

import com.epam.evm.conference.dao.daoInterface.QuestionPersistentDao;
import com.epam.evm.conference.dao.extractor.FieldExtractor;
import com.epam.evm.conference.dao.extractor.QuestionFieldExtractor;
import com.epam.evm.conference.dao.mapper.QuestionRowMapper;
import com.epam.evm.conference.dao.mapper.RowMapper;
import com.epam.evm.conference.model.Question;

import java.sql.Connection;

public class QuestionPersistentDaoImpl extends AbstractPersistentDao<Question> implements QuestionPersistentDao {

    private final static String SELECT_ALL_QUESTIONS = "SELECT * FROM question ORDER BY id DESC";
    private final static String TABLE = "question";
    private final static RowMapper<Question> MAPPER = new QuestionRowMapper();
    private final static FieldExtractor<Question> EXTRACTOR = new QuestionFieldExtractor();

    public QuestionPersistentDaoImpl(Connection connection) {
        super(connection, MAPPER, EXTRACTOR, TABLE, SELECT_ALL_QUESTIONS);
    }
}
