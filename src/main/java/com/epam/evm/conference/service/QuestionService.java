package com.epam.evm.conference.service;

import com.epam.evm.conference.dao.daoInterface.MessageDao;
import com.epam.evm.conference.dao.daoInterface.QuestionDao;
import com.epam.evm.conference.dao.helper.DaoHelper;
import com.epam.evm.conference.dao.helper.DaoHelperFactory;
import com.epam.evm.conference.exception.DaoException;
import com.epam.evm.conference.exception.FieldValidationException;
import com.epam.evm.conference.exception.ServiceException;
import com.epam.evm.conference.model.Question;
import com.epam.evm.conference.validator.FieldUtils;

import java.util.List;

public class QuestionService {

    private final DaoHelperFactory factory;
    private final FieldUtils validator;

    public QuestionService(DaoHelperFactory factory, FieldUtils validator) {
        this.factory = factory;
        this.validator = validator;
    }

    public List<Question> findQuestionsByUserId(Long userId) throws ServiceException {

        try (DaoHelper helper = factory.create()) {
            QuestionDao questionDao = helper.createQuestionDao();
            return questionDao.findQuestionsByUserId(userId);
        } catch (DaoException e) {
            throw new ServiceException("Find question error", e);
        }
    }

    public List<Question> findAllQuestionWithUserLogin(int limit, int offset) throws ServiceException {

        try (DaoHelper helper = factory.create()) {
            QuestionDao questionDao = helper.createQuestionDao();
            return questionDao.findEntityForPagination(limit, offset);
        } catch (DaoException e) {
            throw new ServiceException("Find question error", e);
        }
    }

    public Long countRows() throws ServiceException {

        try (DaoHelper helper = factory.create()) {
            QuestionDao questionDao = helper.createQuestionDao();
            return questionDao.countRows();
        } catch (DaoException e) {
            throw new ServiceException("Count question rows error", e);
        }
    }

    public void saveQuestion(Long userId, Long conferenceId, String content) throws ServiceException {

        if (!validator.isValidMediumLength(content)) {
            throw new FieldValidationException("Field does not match format");
        }
        Question question = new Question(null, userId, conferenceId, content);

        try (DaoHelper helper = factory.create()) {
            QuestionDao questionDao = helper.createQuestionDao();
            questionDao.save(question);
        } catch (DaoException e) {
            throw new ServiceException("Save question error", e);
        }
    }
}
