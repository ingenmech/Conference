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

    private final static String INSERT_QUESTION = "INSERT INTO question(user_id, content) VALUES (?, ?)";
    private final static String SELECT_QUESTION_BY_USER_ID =
            "SELECT question.id, question.user_id, question.content, user.login AS user_login FROM question " +
                    "LEFT JOIN user ON question.user_id = user.id " +
                    "WHERE user_id = ? ORDER BY id DESC";
    private final static String SELECT_ALL_QUESTIONS_WITH_LOGIN =
            "SELECT question.id, question.user_id, question.content, user.login AS user_login FROM question " +
                    "LEFT JOIN user ON question.user_id = user.id ORDER BY id DESC";
    private final static String UPDATE_QUESTION = "";
    private final static String TABLE = "question";

    private final static RowMapper<Question> MAPPER = new QuestionRowMapper();
    private final static FieldExtractor<Question> EXTRACTOR = new QuestionFieldExtractor();

    public QuestionDaoImpl(Connection connection) {
        super(connection, MAPPER, EXTRACTOR, TABLE, INSERT_QUESTION, UPDATE_QUESTION, SELECT_ALL_QUESTIONS_WITH_LOGIN);
    }

    @Override
    public List<Question> findQuestionsByUserId(Long userId) throws DaoException {
        return executeQuery(SELECT_QUESTION_BY_USER_ID, userId);
    }
}
