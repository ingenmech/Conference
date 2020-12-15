package com.epam.evm.conference.service;

import com.epam.evm.conference.dao.ConferenceDao;
import com.epam.evm.conference.dao.SectionDao;
import com.epam.evm.conference.dao.TopicDao;
import com.epam.evm.conference.dao.UserDao;
import com.epam.evm.conference.dao.helper.DaoHelper;
import com.epam.evm.conference.dao.helper.DaoHelperFactory;
import com.epam.evm.conference.exception.DaoException;
import com.epam.evm.conference.exception.ServiceException;
import com.epam.evm.conference.model.Conference;
import com.epam.evm.conference.model.Section;
import com.epam.evm.conference.model.Topic;
import com.epam.evm.conference.model.User;

import java.util.List;
import java.util.Optional;

public class GetService {

    private final DaoHelperFactory factory;

    public GetService(DaoHelperFactory factory) {
        this.factory = factory;
    }

    public List<Conference> getAllConferencesWithSections() throws ServiceException {

        try (DaoHelper helper = factory.create()) {
            ConferenceDao conferenceDao = helper.createConferenceDao();
            SectionDao sectionDao = helper.createSectionDao();

            List<Conference> conferences = conferenceDao.getAll();
            List<Section> sections = sectionDao.getAll();
            for (Conference conference : conferences) {
                putSection(conference, sections);
            }
            return conferences;

        } catch (DaoException e) {
            throw new ServiceException("Get conference error", e);
        }
    }

    private void putSection(Conference conference, List<Section> sections) {

        Long conferenceId = conference.getId();
        for (Section section : sections) {
            Long sectionConferenceId = section.getConferenceId();
            if (conferenceId.equals(sectionConferenceId)) {
                conference.addSection(section);
            }
        }
    }

    public List<Topic> getAllTopicsWithUsersSectionsConferences() throws ServiceException {

        try (DaoHelper helper = factory.create()) {
            TopicDao topicDao = helper.createTopicDao();
            List<Topic> topics = topicDao.getAll();

            SectionDao sectionDao = helper.createSectionDao();
            UserDao userDao = helper.createUserDao();
            ConferenceDao conferenceDao = helper.createConferenceDao();

            //TODO something
//                if(sectionWrapper.isEmpty() && userWrapper.isEmpty()) {
//                }

            for (Topic topic : topics) {

                Long sectionId = topic.getSectionId();
                Optional<Section> sectionWrapper = sectionDao.findBiId(sectionId);
                Section section = sectionWrapper.get();

                Long userId = topic.getUserId();
                Optional<User> userWrapper = userDao.findBiId(userId);
                User user = userWrapper.get();

                Long conferenceId = section.getConferenceId();
                Optional<Conference> conferenceWrapper = conferenceDao.findBiId(conferenceId);
                Conference conference = conferenceWrapper.get();

                topic.setConference(conference);
                topic.setSection(section);
                topic.setUser(user);
            }
            return topics;

        } catch (DaoException e) {
            throw new ServiceException("Get topic error", e);
        }
    }
}
