package com.epam.evm.conference.service;

import com.epam.evm.conference.dao.daoInterface.QuestionDao;
import com.epam.evm.conference.dao.helper.DaoHelper;
import com.epam.evm.conference.dao.helper.DaoHelperFactory;
import com.epam.evm.conference.exception.DaoException;
import com.epam.evm.conference.exception.FieldValidationException;
import com.epam.evm.conference.exception.ServiceException;
import com.epam.evm.conference.model.Question;
import com.epam.evm.conference.validator.FieldValidator;

import java.util.List;

public class QuestionService {

    private final static String CONTENT_REGEX = "^.{1,150}$";

    private final DaoHelperFactory factory;
    private final FieldValidator validator;

    public QuestionService(DaoHelperFactory factory, FieldValidator validator) {
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

    public void saveQuestion(Long userId, String content) throws ServiceException {

        if (!validator.isValid(content, CONTENT_REGEX)){
            throw new FieldValidationException("Field does not match format");
        }

        Question question = new Question(null, userId, content);

        try (DaoHelper helper = factory.create()) {

            QuestionDao questionDao = helper.createQuestionDao();
            questionDao.save(question);
        } catch (DaoException e) {
            throw new ServiceException("Save question error", e);
        }
    }
}
