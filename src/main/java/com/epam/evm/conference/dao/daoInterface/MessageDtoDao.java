package com.epam.evm.conference.dao.daoInterface;

import com.epam.evm.conference.exception.DaoException;
import com.epam.evm.conference.model.Message;
import com.epam.evm.conference.model.MessageDto;

import java.util.List;

public interface MessageDtoDao extends Dao<MessageDto> {

    List<MessageDto> findMessagesByQuestionId(Long questionId) throws DaoException;
}
