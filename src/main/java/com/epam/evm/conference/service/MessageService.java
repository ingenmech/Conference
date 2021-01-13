package com.epam.evm.conference.service;

import com.epam.evm.conference.dao.daoInterface.MessageDao;
import com.epam.evm.conference.dao.helper.DaoHelper;
import com.epam.evm.conference.dao.helper.DaoHelperFactory;
import com.epam.evm.conference.exception.DaoException;
import com.epam.evm.conference.exception.FieldValidationException;
import com.epam.evm.conference.exception.ServiceException;
import com.epam.evm.conference.model.Message;
import com.epam.evm.conference.validator.FieldUtils;

import java.time.LocalDateTime;
import java.util.List;

public class MessageService {

    private final DaoHelperFactory factory;
    private final FieldUtils validator;

    public MessageService(DaoHelperFactory factory, FieldUtils validator) {
        this.factory = factory;
        this.validator = validator;
    }

    public List<Message> findMessagesByQuestionId(Long questionId) throws ServiceException {

        try (DaoHelper helper = factory.create()) {

            MessageDao messageDao = helper.createMessageDao();
            return messageDao.findMessagesByQuestionId(questionId);

        } catch (DaoException e) {
            throw new ServiceException("Find question error", e);
        }
    }

    public void saveMessage(Long questionId, Long userId, String content) throws ServiceException {

        if (!validator.isValidLongLength(content)){
            throw new FieldValidationException("Field question content does not match format");
        }

        Message message = new Message(null, questionId, userId, content);

        try (DaoHelper helper = factory.create()) {

            MessageDao messageDao = helper.createMessageDao();
            messageDao.save(message);

        } catch (DaoException e) {
            throw new ServiceException("Save message error", e);
        }
    }
}
