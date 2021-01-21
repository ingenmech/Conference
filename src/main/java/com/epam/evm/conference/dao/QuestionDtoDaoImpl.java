package com.epam.evm.conference.dao;

import com.epam.evm.conference.dao.daoInterface.QuestionDtoDao;
import com.epam.evm.conference.dao.mapper.QuestionDtoRowMapper;
import com.epam.evm.conference.dao.mapper.RowMapper;
import com.epam.evm.conference.exception.DaoException;
import com.epam.evm.conference.model.QuestionDto;

import java.sql.Connection;
import java.util.List;

public class QuestionDtoDaoImpl extends AbstractDao<QuestionDto> implements QuestionDtoDao {

    private final static String SELECT_QUESTION_BY_USER_ID =
            "SELECT question.id, question.content, user.login AS user_login, conference.name AS conference_name FROM question " +
                    "LEFT JOIN user ON question.user_id = user.id " +
                    "LEFT JOIN conference ON question.conference_id = conference.id WHERE user_id = ? ORDER BY id DESC";
    private final static String SELECT_ALL_QUESTIONS_WITH_LOGIN =
            "SELECT question.id, question.content, user.login AS user_login, conference.name AS conference_name FROM question " +
                    "LEFT JOIN user ON question.user_id = user.id " +
                    "LEFT JOIN conference ON question.conference_id = conference.id ORDER BY id DESC";
    private final static String TABLE = "question";

    private final static RowMapper<QuestionDto> MAPPER = new QuestionDtoRowMapper();

    public QuestionDtoDaoImpl(Connection connection) {
        super(connection, MAPPER, TABLE, SELECT_ALL_QUESTIONS_WITH_LOGIN);
    }

    @Override
    public List<QuestionDto> findQuestionsByUserId(Long userId) throws DaoException {
        return executeQuery(SELECT_QUESTION_BY_USER_ID, userId);
    }
}
