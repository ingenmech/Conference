package com.epam.evm.conference.service;

import com.epam.evm.conference.dao.daoInterface.*;
import com.epam.evm.conference.dao.helper.DaoHelper;
import com.epam.evm.conference.dao.helper.DaoHelperFactory;
import com.epam.evm.conference.exception.DaoException;
import com.epam.evm.conference.exception.ServiceException;
import com.epam.evm.conference.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FindService {

    private final DaoHelperFactory factory;

    public FindService(DaoHelperFactory factory) {
        this.factory = factory;
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

    public List<Question> findAllQuestionWithUserLogin() throws ServiceException {

        try (DaoHelper helper = factory.create()) {
            QuestionDao questionDao = helper.createQuestionDao();
            List<Question> questions = questionDao.getAll();

            UserDao userDao = helper.createUserDao();

            for (Question question: questions){
                Long userId = question.getUserId();
                Optional<User> userWrapper = userDao.findBiId(userId);
                if (userWrapper.isPresent()){
                    User user = userWrapper.get();
                    String userLogin = user.getLogin();
                    question.setUserLogin(userLogin);
                }
            }
            return questions;

        } catch (DaoException e) {
            throw new ServiceException("Find question error", e);
        }
    }

//    private void initQuestion(Question question, List<Message> messages){
//
//        Long questionId = question.getId();
//        for (Message message :messages ) {
//            Long messageQuestionId = message.getQuestionId();
//            if (questionId.equals(messageQuestionId)) {
//                question.addMessage(message);
//            }
//        }
//    }

    public List<Request> findAllRequestsWithUsersSectionsConferences() throws ServiceException {

        try (DaoHelper helper = factory.create()) {
            RequestDao requestDao = helper.createTopicDao();
            List<Request> requests = requestDao.getAll();

            return fillRequests(requests);

        } catch (DaoException e) {
            throw new ServiceException("Find topic error", e);
        }
    }

    public List<Request> findAllRequestsByUserId(Long userId) throws ServiceException {

        try (DaoHelper helper = factory.create()) {
            RequestDao requestDao = helper.createTopicDao();
            List<Request> requests = requestDao.findAllRequestsByUserId(userId);

            return fillRequests(requests);
        } catch (DaoException e) {
            throw new ServiceException("Find all users requests error", e);
        }
    }

    private List<Request> fillRequests(List<Request> requests) throws DaoException {

        try (DaoHelper helper = factory.create()) {
            SectionDao sectionDao = helper.createSectionDao();
            UserDao userDao = helper.createUserDao();
            ConferenceDao conferenceDao = helper.createConferenceDao();

            List<Request> filledRequest = new ArrayList<>();
            for (Request request : requests) {

                Long sectionId = request.getSectionId();
                Optional<Section> sectionWrapper = sectionDao.findBiId(sectionId);

                Long userId = request.getUserId();
                Optional<User> userWrapper = userDao.findBiId(userId);

                if (sectionWrapper.isPresent() && userWrapper.isPresent()) {
                    Section section = sectionWrapper.get();
                    User user = userWrapper.get();
                    Long conferenceId = section.getConferenceId();

                    Optional<Conference> conferenceWrapper = conferenceDao.findBiId(conferenceId);
                    if (conferenceWrapper.isPresent()) {
                        Conference conference = conferenceWrapper.get();

                        request.setConference(conference);
                        request.setSection(section);
                        request.setUser(user);
                    }
                    filledRequest.add(request);
                }
            }
            return filledRequest;
        }
    }


}
