package com.epam.evm.conference.dao.daoInterface;

import com.epam.evm.conference.exception.DaoException;
import com.epam.evm.conference.model.Message;

import java.util.List;

public interface MessageDao extends Dao<Message> {

    List<Message> findMessagesByQuestionId(Long questionId) throws DaoException;
}
