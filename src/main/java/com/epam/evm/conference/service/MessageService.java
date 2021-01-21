package com.epam.evm.conference.service;

import com.epam.evm.conference.dao.daoInterface.MessageDtoDao;
import com.epam.evm.conference.dao.daoInterface.MessagePersistentDao;
import com.epam.evm.conference.dao.helper.DaoHelper;
import com.epam.evm.conference.dao.helper.DaoHelperFactory;
import com.epam.evm.conference.exception.DaoException;
import com.epam.evm.conference.exception.FieldValidationException;
import com.epam.evm.conference.exception.ServiceException;
import com.epam.evm.conference.model.Message;
import com.epam.evm.conference.model.MessageDto;
import com.epam.evm.conference.utils.FieldUtils;

import java.util.List;

public class MessageService {

    private final DaoHelperFactory factory;

    public MessageService(DaoHelperFactory factory) {
        this.factory = factory;
    }

    public List<MessageDto> findMessagesByQuestionId(Long questionId) throws ServiceException {

        try (DaoHelper helper = factory.create()) {
            MessageDtoDao messageDao = helper.createMessageDtoDao();
            return messageDao.findMessagesByQuestionId(questionId);
        } catch (DaoException e) {
            throw new ServiceException("Find question error", e);
        }
    }

    public void saveMessage(Long questionId, Long userId, String content) throws ServiceException {

        if (!FieldUtils.isValidLength(content, FieldUtils.LONG_SIZE)) {
            throw new FieldValidationException("Field question content does not match format");
        }
        Message message = new Message(null, questionId, userId, content);

        try (DaoHelper helper = factory.create()) {
            MessagePersistentDao messageDao = helper.createMessagePersistentDao();
            messageDao.save(message);
        } catch (DaoException e) {
            throw new ServiceException("Save message error", e);
        }
    }
}
