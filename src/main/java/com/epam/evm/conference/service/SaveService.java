package com.epam.evm.conference.service;

import com.epam.evm.conference.dao.daoInterface.*;
import com.epam.evm.conference.dao.helper.DaoHelper;
import com.epam.evm.conference.dao.helper.DaoHelperFactory;
import com.epam.evm.conference.exception.DaoException;
import com.epam.evm.conference.exception.ServiceException;
import com.epam.evm.conference.model.*;

import java.util.Optional;

public class SaveService {

    private final DaoHelperFactory factory;

    public SaveService(DaoHelperFactory factory) {
        this.factory = factory;
    }

    public void saveConferenceWithSection(Conference conference) throws ServiceException {

        try (DaoHelper helper = factory.create()) {

            helper.startTransaction();
            ConferenceDao conferenceDao = helper.createConferenceDao();
            Optional<Long> conferenceId = conferenceDao.save(conference);

            SectionDao sectionDao = helper.createSectionDao();
            for (int i = 0; i < conference.sizeSections(); i++) {
                Section section = conference.getSection(i);
                Long id = conferenceId.get();
                section.setConferenceId(id);
                sectionDao.save(section);
            }
            helper.endTransaction();

        } catch (DaoException e) {
            throw new ServiceException("Save conference error", e);
        }
    }

    public void saveTopic(Topic topic) throws ServiceException {

        try (DaoHelper helper = factory.create()) {

            TopicDao topicDao = helper.createTopicDao();
            Optional<Long> id = topicDao.save(topic);

        } catch (DaoException e) {
            throw new ServiceException("Save topic error", e);
        }
    }

    public void saveMessage(Message message) throws ServiceException {

        try (DaoHelper helper = factory.create()) {

            MessageDao messageDao = helper.createMessageDao();
            Optional<Long> id = messageDao.save(message);

        } catch (DaoException e) {
            throw new ServiceException("Save message error", e);
        }
    }

    public void saveQuestion(Question question) throws ServiceException {

        try (DaoHelper helper = factory.create()) {

            QuestionDao questionDao = helper.createQuestionDao();
            Optional<Long> id = questionDao.save(question);
        } catch (DaoException e) {
            throw new ServiceException("Save question error", e);
        }
    }

}
