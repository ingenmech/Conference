package com.epam.evm.conference.dao.daoInterface;

import com.epam.evm.conference.exception.DaoException;
import com.epam.evm.conference.model.MessageDto;

import java.util.List;

/**
 * The MessageDtoDao interface extends Dao interface. Contains specific find message method
 * for working with the database.
 *
 * @version 1.0
 */
public interface MessageDtoDao extends Dao<MessageDto> {

    List<MessageDto> findMessagesByQuestionId(Long questionId) throws DaoException;
}
