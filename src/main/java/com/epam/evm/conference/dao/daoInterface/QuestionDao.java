package com.epam.evm.conference.dao.daoInterface;

import com.epam.evm.conference.exception.DaoException;
import com.epam.evm.conference.model.Question;

import java.util.List;

public interface QuestionDao extends Dao<Question> {

    List<Question> findQuestionsByUserId(Long userId) throws DaoException;
}
