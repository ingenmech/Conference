package com.epam.evm.conference.service;

import com.epam.evm.conference.dao.daoInterface.QuestionDtoDao;
import com.epam.evm.conference.dao.daoInterface.QuestionPersistentDao;
import com.epam.evm.conference.dao.helper.DaoHelper;
import com.epam.evm.conference.dao.helper.DaoHelperFactory;
import com.epam.evm.conference.exception.DaoException;
import com.epam.evm.conference.exception.FieldValidationException;
import com.epam.evm.conference.exception.ServiceException;
import com.epam.evm.conference.model.Question;
import com.epam.evm.conference.model.QuestionDto;
import com.epam.evm.conference.utils.FieldUtils;

import java.util.List;

public class QuestionService {

    private final DaoHelperFactory factory;

    public QuestionService(DaoHelperFactory factory) {
        this.factory = factory;
    }

    public List<QuestionDto> findQuestionsByUserId(Long userId) throws ServiceException {

        try (DaoHelper helper = factory.create()) {
            QuestionDtoDao questionDao = helper.createQuestionDtoDao();
            return questionDao.findQuestionsByUserId(userId);
        } catch (DaoException e) {
            throw new ServiceException("Find question error", e);
        }
    }

    public List<QuestionDto> findAllQuestionWithUserLogin(int limit, int offset) throws ServiceException {

        try (DaoHelper helper = factory.create()) {
            QuestionDtoDao questionDao = helper.createQuestionDtoDao();
            return questionDao.findEntityForPagination(limit, offset);
        } catch (DaoException e) {
            throw new ServiceException("Find question error", e);
        }
    }

    public Long countRows() throws ServiceException {

        try (DaoHelper helper = factory.create()) {
            QuestionPersistentDao questionDao = helper.createQuestionPersistentDao();
            return questionDao.countRows();
        } catch (DaoException e) {
            throw new ServiceException("Count question rows error", e);
        }
    }

    public void saveQuestion(Long userId, Long conferenceId, String content) throws ServiceException {

        if (!FieldUtils.isValidLength(content, FieldUtils.MID_SIZE)) {
            throw new FieldValidationException("Field does not match format");
        }
        Question question = new Question(null, userId, conferenceId, content);

        try (DaoHelper helper = factory.create()) {
            QuestionPersistentDao questionDao = helper.createQuestionPersistentDao();
            questionDao.save(question);
        } catch (DaoException e) {
            throw new ServiceException("Save question error", e);
        }
    }
}
