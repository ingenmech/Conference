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
import com.epam.evm.conference.model.SectionStatus;
import com.epam.evm.conference.validator.FieldUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ConferenceService {

    private final static Logger LOGGER = LogManager.getLogger(ConferenceService.class);

    private final DaoHelperFactory factory;
    private final FieldUtils validator;

    public ConferenceService(DaoHelperFactory factory, FieldUtils validator) {
        this.factory = factory;
        this.validator = validator;
    }

    public Long countActualConferences() throws ServiceException {

        try (DaoHelper helper = factory.create()) {
            ConferenceDao conferenceDao = helper.createConferenceDao();
            ZoneId zoneId = ZoneId.systemDefault();
            LocalDateTime actualDateTime = LocalDateTime.now(zoneId);
            return conferenceDao.countActualConference(actualDateTime);
        } catch (DaoException e) {
            throw new ServiceException("Count conference error", e);
        }
    }

    public Conference findConferencesWithSectionsById(Long id) throws ServiceException {

        try (DaoHelper helper = factory.create()) {
            ConferenceDao conferenceDao = helper.createConferenceDao();
            SectionDao sectionDao = helper.createSectionDao();

            Optional<Conference> conferenceOptional = conferenceDao.findById(id);
            if (conferenceOptional.isEmpty()) {
                throw new ServiceException("Find conference by id error");
            }
            Conference conference = conferenceOptional.get();
            Long conferenceId = conference.getId();
            List<Section> sections = sectionDao.findSectionsByConferenceId(conferenceId);
            initConference(conference, sections);
            return conference;

        } catch (DaoException e) {
            throw new ServiceException("Find conference error", e);
        }
    }

    public List<Conference> findActualConferencesForPagination(int limit, int offset) throws ServiceException {

        try (DaoHelper helper = factory.create()) {
            ConferenceDao conferenceDao = helper.createConferenceDao();
            SectionDao sectionDao = helper.createSectionDao();
            ZoneId zoneId = ZoneId.systemDefault();
            LocalDateTime actualDateTime = LocalDateTime.now(zoneId);
            List<Conference> conferences = conferenceDao.findActualConferencesForPagination(actualDateTime, limit, offset);
            List<Section> sections = sectionDao.findActualSections();
            for (Conference conference : conferences) {
                initConference(conference, sections);
            }
            return conferences;

        } catch (DaoException e) {
            throw new ServiceException("Find conference error", e);
        }
    }

    public List<Conference> findActualConferencesWithSections() throws ServiceException {

        try (DaoHelper helper = factory.create()) {
            ConferenceDao conferenceDao = helper.createConferenceDao();
            SectionDao sectionDao = helper.createSectionDao();
            ZoneId zoneId = ZoneId.systemDefault();
            LocalDateTime actualDateTime = LocalDateTime.now(zoneId);
            List<Conference> conferences = conferenceDao.findActualConferences(actualDateTime);
            List<Section> sections = sectionDao.findActualSections();
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

    public void updateConferenceWithSection(Long conferenceId, String name, LocalDateTime dateTime, List<Long> sectionsId,
                                            String[] sectionNames, String[] statuses) throws ServiceException {
        if (!validator.isValidMediumLength(name)) {
            throw new FieldValidationException("Field conference name does not match format");
        }
        if (!validator.isValidMediumLength(sectionNames)) {
            throw new FieldValidationException("Field section names does not match format");
        }
        if (!validator.isValidMediumLength(statuses)) {
            throw new FieldValidationException("Field statuses does not match format");
        }

        DaoHelper helper = null;
        try {
            helper = factory.create();
            helper.setAutoCommit(false);
            ConferenceDao conferenceDao = helper.createConferenceDao();
            SectionDao sectionDao = helper.createSectionDao();

            Conference conference = new Conference(conferenceId, name, dateTime);
            conferenceDao.save(conference);
            List<Section> sections = createSections(conferenceId, sectionsId, sectionNames, statuses);
            for (Section section : sections) {
                sectionDao.save(section);
            }
            helper.endTransaction();
        } catch (DaoException e) {
            LOGGER.error(e.getMessage(), e);
            try {
                helper.rollback();
            } catch (DaoException exception) {
                LOGGER.error(exception.getMessage(), exception);
            }
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

    private List<Section> createSections(Long conferenceId, List<Long> sectionsId, String[] sectionNames, String[] statuses) {

        List<Section> sections = new ArrayList<>();
        for (int i = 0; i < sectionNames.length; i++) {
            Long id = null;
            if (i < sectionsId.size()) {
                id = sectionsId.get(i);
            }
            SectionStatus status = SectionStatus.valueOf(statuses[i]);
            sections.add(new Section(id, conferenceId, sectionNames[i], status));
        }
        return sections;
    }

    public void saveConferenceWithSection(String name, LocalDateTime dateTime, String[] sectionNames) throws ServiceException {

        if (!validator.isValidMediumLength(name)) {
            throw new FieldValidationException("Field conference name does not match format");
        }
        if (!validator.isValidMediumLength(sectionNames)) {
            throw new FieldValidationException("Fields section names does not match format");
        }

        DaoHelper helper = null;
        try {
            helper = factory.create();
            helper.setAutoCommit(false);
            ConferenceDao conferenceDao = helper.createConferenceDao();
            Conference conference = new Conference(null, name, dateTime);
            Optional<Long> conferenceId = conferenceDao.save(conference);
            if (conferenceId.isEmpty()) {
                throw new DaoException("Conference don't save");
            }
            Long id = conferenceId.get();

            SectionDao sectionDao = helper.createSectionDao();
            List<Section> sections = createSections(id, sectionNames);
            for (Section section : sections) {
                sectionDao.save(section);
            }
            helper.endTransaction();
        } catch (DaoException e) {
            LOGGER.error(e.getMessage(), e);
            try {
                helper.rollback();
            } catch (DaoException exception) {
                LOGGER.error(exception.getMessage(), exception);
            }
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

    private List<Section> createSections(Long conferenceId, String[] sectionNames) {

        List<Section> sections = new ArrayList<>();
        for (String value : sectionNames) {
            sections.add(new Section(null, conferenceId, value, SectionStatus.ACTUAL));
        }
        return sections;
    }


}
