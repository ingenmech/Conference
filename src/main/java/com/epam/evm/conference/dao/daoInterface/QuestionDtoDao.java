package com.epam.evm.conference.dao.daoInterface;

import com.epam.evm.conference.exception.DaoException;
import com.epam.evm.conference.model.QuestionDto;

import java.util.List;

/**
 * The {@code QuestionDtoDao} interface extends Dao interface
 * and represents specific method signature for find question in database.
 *
 * @version 1.0
 */
public interface QuestionDtoDao extends Dao<QuestionDto> {

    List<QuestionDto> findQuestionsByUserId(Long userId) throws DaoException;
}
