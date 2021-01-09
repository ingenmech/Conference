package com.epam.evm.conference.service;

import com.epam.evm.conference.dao.daoInterface.ConferenceDao;
import com.epam.evm.conference.dao.daoInterface.SectionDao;
import com.epam.evm.conference.dao.helper.DaoHelper;
import com.epam.evm.conference.dao.helper.DaoHelperFactory;
import com.epam.evm.conference.exception.DaoException;
import com.epam.evm.conference.exception.FieldValidationException;
import com.epam.evm.conference.exception.ServiceException;
import com.epam.evm.conference.model.Conference;
import com.epam.evm.conference.model.Section;
import com.epam.evm.conference.validator.FieldValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ConferenceService {

    private final static Logger LOGGER = LogManager.getLogger(ConferenceService.class);
    private final static String REGEX = "^.{1,150}$";

    private final DaoHelperFactory factory;
    private final FieldValidator validator;

    public ConferenceService(DaoHelperFactory factory, FieldValidator validator) {
        this.factory = factory;
        this.validator=validator;
    }

    public List<Conference> findConferencesForPagination(int limit, int offset) throws ServiceException {

        try (DaoHelper helper = factory.create()) {
            ConferenceDao conferenceDao = helper.createConferenceDao();
            SectionDao sectionDao = helper.createSectionDao();

            List<Conference> conferences = conferenceDao.findEntityForPagination(limit, offset);
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

    public void saveConferenceWithSection(String name, LocalDateTime localDateTime, String[] sectionNames) throws ServiceException {

        if(!validator.isValid(name, REGEX) || !validator.isValid(sectionNames, REGEX)){
            throw new FieldValidationException("Field does not match format");
        }

        Conference conference = buildConference(name, localDateTime, sectionNames);

        DaoHelper helper = null;
        try {
            helper = factory.create();
            helper.setAutoCommit(false);
            ConferenceDao conferenceDao = helper.createConferenceDao();
            Optional<Long> conferenceId = conferenceDao.save(conference);
            if (conferenceId.isEmpty()) {
                throw new ServiceException("Conference don't save");
            }
            Long id = conferenceId.get();

            SectionDao sectionDao = helper.createSectionDao();
            for (int i = 0; i < conference.sizeSections(); i++) {
                Section section = conference.getSection(i);
                section.setConferenceId(id);
                sectionDao.save(section);
            }
            helper.endTransaction();

        } catch (DaoException e) {
            throw new ServiceException("Save conference error", e);
        } finally {
            if (helper != null) {
                try {
                    helper.setAutoCommit(true);
                    helper.close();
                } catch (DaoException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
        }
    }

    private Conference buildConference(String name, LocalDateTime localDateTime, String[] sectionNames){

        List<Section> sections = new ArrayList<>();
        for (String value : sectionNames) {
            sections.add(new Section(null, null, value));
        }
        return new Conference(null, name, localDateTime, sections);
    }
}
