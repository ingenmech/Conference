package com.epam.evm.conference.service;

import com.epam.evm.conference.dao.daoInterface.QuestionDao;
import com.epam.evm.conference.dao.helper.DaoHelper;
import com.epam.evm.conference.dao.helper.DaoHelperFactory;
import com.epam.evm.conference.exception.DaoException;
import com.epam.evm.conference.exception.ServiceException;
import com.epam.evm.conference.model.Question;

import java.util.List;

public class QuestionService {

    private final DaoHelperFactory factory;

    public QuestionService(DaoHelperFactory factory) {
        this.factory = factory;
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

    public void saveQuestion(Question question) throws ServiceException {

        try (DaoHelper helper = factory.create()) {

            QuestionDao questionDao = helper.createQuestionDao();
            questionDao.save(question);
        } catch (DaoException e) {
            throw new ServiceException("Save question error", e);
        }
    }
}
