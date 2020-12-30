package com.epam.evm.conference.service;

import com.epam.evm.conference.dao.daoInterface.ConferenceDao;
import com.epam.evm.conference.dao.daoInterface.SectionDao;
import com.epam.evm.conference.dao.helper.DaoHelper;
import com.epam.evm.conference.dao.helper.DaoHelperFactory;
import com.epam.evm.conference.exception.DaoException;
import com.epam.evm.conference.exception.ServiceException;
import com.epam.evm.conference.model.Conference;
import com.epam.evm.conference.model.Section;

import java.util.List;
import java.util.Optional;

public class ConferenceService {

    private final DaoHelperFactory factory;

    public ConferenceService(DaoHelperFactory factory) {
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

    public void saveConferenceWithSection(Conference conference) throws ServiceException {

        DaoHelper helper = null;
        try {
            helper = factory.create();
            helper.startTransaction();
            ConferenceDao conferenceDao = helper.createConferenceDao();
            Optional<Long> conferenceId = conferenceDao.save(conference);

            SectionDao sectionDao = helper.createSectionDao();
            for (int i = 0; i < conference.sizeSections(); i++) {
                Section section = conference.getSection(i);
                Long id = conferenceId.get();
                //TODO skip or throw exception
                section.setConferenceId(id);
                sectionDao.save(section);
            }
            helper.endTransaction();

        } catch (DaoException e) {
            try {
                helper.rollback();
            } catch (DaoException stack) {
                throw new ServiceException("", stack);
            }
            throw new ServiceException("Save conference error", e);
        } finally {
            if (helper != null) {
                helper.close();
            }
        }
    }
}
