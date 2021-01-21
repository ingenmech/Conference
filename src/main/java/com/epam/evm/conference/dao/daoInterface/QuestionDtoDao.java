package com.epam.evm.conference.dao.daoInterface;

import com.epam.evm.conference.exception.DaoException;
import com.epam.evm.conference.model.Question;
import com.epam.evm.conference.model.QuestionDto;

import java.util.List;

public interface QuestionDtoDao extends Dao<QuestionDto> {

    List<QuestionDto> findQuestionsByUserId(Long userId) throws DaoException;
}
