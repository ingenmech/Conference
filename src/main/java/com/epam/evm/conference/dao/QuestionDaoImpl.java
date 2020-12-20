package com.epam.evm.conference.dao;

import com.epam.evm.conference.dao.daoInterface.QuestionDao;
import com.epam.evm.conference.dao.extractor.FieldExtractor;
import com.epam.evm.conference.dao.extractor.QuestionFieldExtractor;
import com.epam.evm.conference.dao.mapper.QuestionRowMapper;
import com.epam.evm.conference.dao.mapper.RowMapper;
import com.epam.evm.conference.exception.DaoException;
import com.epam.evm.conference.model.Question;

import java.sql.Connection;
import java.util.List;

public class QuestionDaoImpl extends AbstractDao<Question> implements QuestionDao {

    private final static String INSERT_QUESTION = "INSERT INTO question(USER_ID, CONTENT) VALUES (?, ?)";
    private final static String SELECT_QUESTION_BY_USER_ID =
            "SELECT * FROM question WHERE user_id = ? ORDER BY id ";
    private final static String UPDATE_QUESTION = "";
    private final static String TABLE = "question";

    private final static RowMapper<Question> MAPPER = new QuestionRowMapper();
    private final static FieldExtractor<Question> EXTRACTOR = new QuestionFieldExtractor();

    public QuestionDaoImpl(Connection connection) {
        super(connection, MAPPER, EXTRACTOR, TABLE, INSERT_QUESTION, UPDATE_QUESTION);
    }

    @Override
    public List<Question> findQuestionsByUserId(Long userId) throws DaoException {
        return executeQuery(SELECT_QUESTION_BY_USER_ID, userId);
    }
}
