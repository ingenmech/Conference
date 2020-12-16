package com.epam.evm.conference.service;

import com.epam.evm.conference.dao.ConferenceDao;
import com.epam.evm.conference.dao.SectionDao;
import com.epam.evm.conference.dao.TopicDao;
import com.epam.evm.conference.dao.helper.DaoHelper;
import com.epam.evm.conference.dao.helper.DaoHelperFactory;
import com.epam.evm.conference.exception.DaoException;
import com.epam.evm.conference.exception.ServiceException;
import com.epam.evm.conference.model.Conference;
import com.epam.evm.conference.model.Section;
import com.epam.evm.conference.model.Topic;

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
            topicDao.save(topic);

        } catch (DaoException e) {
            throw new ServiceException("Save topic error", e);
        }
    }

}
