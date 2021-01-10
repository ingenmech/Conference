package com.epam.evm.conference.service;

import com.epam.evm.conference.dao.daoInterface.MessageDao;
import com.epam.evm.conference.dao.helper.DaoHelper;
import com.epam.evm.conference.dao.helper.DaoHelperFactory;
import com.epam.evm.conference.exception.DaoException;
import com.epam.evm.conference.exception.FieldValidationException;
import com.epam.evm.conference.exception.ServiceException;
import com.epam.evm.conference.model.Message;
import com.epam.evm.conference.validator.NumberUtils;

import java.time.LocalDateTime;
import java.util.List;

public class MessageService {

    private final static String CONTENT_REGEX = "^.{1,300}$";

    private final DaoHelperFactory factory;
    private final NumberUtils validator;

    public MessageService(DaoHelperFactory factory, NumberUtils validator) {
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

    public void saveMessage(Long questionId, Long userId, LocalDateTime dateTime, String content) throws ServiceException {

        if (!validator.isValid(content, CONTENT_REGEX)){
            throw new FieldValidationException("Field does not match format");
        }

        Message message = new Message(null, questionId, userId, dateTime, content);

        try (DaoHelper helper = factory.create()) {

            MessageDao messageDao = helper.createMessageDao();
            messageDao.save(message);

        } catch (DaoException e) {
            throw new ServiceException("Save message error", e);
        }
    }
}
