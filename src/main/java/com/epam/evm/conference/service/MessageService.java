package com.epam.evm.conference.service;

import com.epam.evm.conference.dao.daoInterface.MessageDao;
import com.epam.evm.conference.dao.helper.DaoHelper;
import com.epam.evm.conference.dao.helper.DaoHelperFactory;
import com.epam.evm.conference.exception.DaoException;
import com.epam.evm.conference.exception.ServiceException;
import com.epam.evm.conference.model.Message;

import java.util.List;

public class MessageService {

    private final DaoHelperFactory factory;

    public MessageService(DaoHelperFactory factory) {
        this.factory = factory;
    }

    public List<Message> findMessagesByQuestionId(Long questionId) throws ServiceException {

        try (DaoHelper helper = factory.create()) {

            MessageDao messageDao = helper.createMessageDao();
            return messageDao.findMessagesByQuestionId(questionId);

        } catch (DaoException e) {
            throw new ServiceException("Find question error", e);
        }
    }

    public void saveMessage(Message message) throws ServiceException {

        try (DaoHelper helper = factory.create()) {

            MessageDao messageDao = helper.createMessageDao();
            messageDao.save(message);

        } catch (DaoException e) {
            throw new ServiceException("Save message error", e);
        }
    }
}
