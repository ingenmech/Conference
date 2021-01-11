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
import com.epam.evm.conference.validator.FieldUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ConferenceService {

    private final static Logger LOGGER = LogManager.getLogger(ConferenceService.class);

    private final DaoHelperFactory factory;
    private final FieldUtils validator;

    public ConferenceService(DaoHelperFactory factory, FieldUtils validator) {
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

    public Conference findConferencesWithSectionsById(Long id) throws ServiceException {

        try (DaoHelper helper = factory.create()) {

            ConferenceDao conferenceDao = helper.createConferenceDao();
            SectionDao sectionDao = helper.createSectionDao();

            Optional<Conference> conferenceOptional = conferenceDao.findBiId(id);
            if(conferenceOptional.isEmpty()){
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

    public List<Conference> findAllConferencesWithSections() throws ServiceException {

        try (DaoHelper helper = factory.create()) {

            ConferenceDao conferenceDao = helper.createConferenceDao();
            SectionDao sectionDao = helper.createSectionDao();

            List<Conference> conferences = conferenceDao.findAll();
            List<Section> sections = sectionDao.findAll();
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
                                            String[] sectionNames) throws ServiceException {
        if(!validator.isValidMediumLength(name)){
            throw new FieldValidationException("Field conference name does not match format");
        }
        if(!validator.isValidMediumLength(sectionNames)){
            throw new FieldValidationException("Field section names does not match format");
        }

        Conference conference = new Conference(conferenceId, name, dateTime);
        List<Section> sections = createSections(conferenceId, sectionsId, sectionNames);

        DaoHelper helper = null;
        try {
            helper = factory.create();
            helper.setAutoCommit(false);
            ConferenceDao conferenceDao = helper.createConferenceDao();

            SectionDao sectionDao = helper.createSectionDao();
            conferenceDao.save(conference);
            for (Section section : sections) {
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

    private List<Section> createSections(Long conferenceId, List<Long> sectionsId, String[] sectionNames){

        List<Section> sections = new ArrayList<>();
        for (int i = 0; i < sectionNames.length; i++) {
            sections.add(new Section(sectionsId.get(i), conferenceId, sectionNames[i]));
        }
        return sections;
    }

    public void saveConferenceWithSection(String name, LocalDateTime dateTime, String[] sectionNames) throws ServiceException {

        if(!validator.isValidMediumLength(name)){
            throw new FieldValidationException("Field conference name does not match format");
        }
        if(!validator.isValidMediumLength(sectionNames)){
            throw new FieldValidationException("Fields section names does not match format");
        }
        Conference conference = new Conference(null, name, dateTime);
        List<Section> sections = createSections(sectionNames);

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
            for (Section section : sections) {
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

    private List<Section> createSections(String[] sectionNames){

        List<Section> sections = new ArrayList<>();
        for (String value : sectionNames) {
            sections.add(new Section(null, null, value));
        }
        return sections;
    }


}
