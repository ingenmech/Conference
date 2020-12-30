package com.epam.evm.conference.service;

import com.epam.evm.conference.dao.daoInterface.*;
import com.epam.evm.conference.dao.helper.DaoHelper;
import com.epam.evm.conference.dao.helper.DaoHelperFactory;
import com.epam.evm.conference.exception.DaoException;
import com.epam.evm.conference.exception.ServiceException;
import com.epam.evm.conference.model.*;

import java.util.List;

public class FindService {

    private final DaoHelperFactory factory;

    public FindService(DaoHelperFactory factory) {
        this.factory = factory;
    }

    public List<Conference> findConferencesWithLimit(int limit, int offset) throws ServiceException {

        try (DaoHelper helper = factory.create()) {
            ConferenceDao conferenceDao = helper.createConferenceDao();
            SectionDao sectionDao = helper.createSectionDao();

            List<Conference> conferences = conferenceDao.findEntityByLimit(limit, offset);
            for (Conference conference : conferences) {
                Long conferenceId = conference.getId();
                List<Section> sections = sectionDao.findSectionsByConferenceId(conferenceId);
                initConference(conference, sections);
            }
            return conferences;

        } catch (DaoException e) {
            throw new ServiceException("Find conference by limit error", e);
        }
    }

    public List<Conference> findAllConferencesWithSections() throws ServiceException {

        try (DaoHelper helper = factory.create()) {
            ConferenceDao conferenceDao = helper.createConferenceDao();
            SectionDao sectionDao = helper.createSectionDao();

            List<Conference> conferences = conferenceDao.getAll();
            List<Section> sections = sectionDao.getAll();
            for (Conference conference : conferences) {
                initConference(conference, sections);
            }
            return conferences;

        } catch (DaoException e) {
            throw new ServiceException("Find conference error", e);
        }
    }

    private void initConference(Conference conference, List<Section> sections) {

        Long conferenceId = conference.getId();
        for (Section section : sections) {
            Long sectionConferenceId = section.getConferenceId();
            if (conferenceId.equals(sectionConferenceId)) {
                conference.addSection(section);
            }
        }
    }

    public List<Question> findQuestionsByUserId(Long userId) throws ServiceException{

        try (DaoHelper helper = factory.create()) {

            QuestionDao questionDao = helper.createQuestionDao();
            return questionDao.findQuestionsByUserId(userId);

        } catch (DaoException e) {
            throw new ServiceException("Find question error", e);
        }
    }

    public List<Message> findMessagesByQuestionId(Long questionId) throws ServiceException {

        try (DaoHelper helper = factory.create()) {

            MessageDao messageDao = helper.createMessageDao();
            return messageDao.findMessagesByQuestionId(questionId);

        } catch (DaoException e) {
            throw new ServiceException("Find question error", e);
        }
    }

    public List<Question> findAllQuestionWithUserLogin(int limit, int offset) throws ServiceException {

        try (DaoHelper helper = factory.create()) {

            QuestionDao questionDao = helper.createQuestionDao();
            return questionDao.findEntityByLimit(limit, offset);

        } catch (DaoException e) {
            throw new ServiceException("Find question error", e);
        }
    }

    public List<Request> findAllRequestsWithUsersSectionsConferences(int limit, int offset) throws ServiceException {

        try (DaoHelper helper = factory.create()) {

            RequestDao requestDao = helper.createTopicDao();
            return  requestDao.findEntityByLimit(limit, offset);

        } catch (DaoException e) {
            throw new ServiceException("Find all users requests error", e);
        }
    }

    public List<Request> findAllRequestsByUserId(Long userId) throws ServiceException {

        try (DaoHelper helper = factory.create()) {

            RequestDao requestDao = helper.createTopicDao();
            return requestDao.findAllRequestsByUserId(userId);

        } catch (DaoException e) {
            throw new ServiceException("Find all users requests by id error", e);
        }
    }
}
