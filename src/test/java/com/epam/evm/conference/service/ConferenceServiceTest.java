package com.epam.evm.conference.service;

import com.epam.evm.conference.dao.daoInterface.ConferenceDao;
import com.epam.evm.conference.dao.daoInterface.SectionDao;
import com.epam.evm.conference.dao.helper.DaoHelper;
import com.epam.evm.conference.dao.helper.DaoHelperFactory;
import com.epam.evm.conference.exception.DaoException;
import com.epam.evm.conference.exception.ServiceException;
import com.epam.evm.conference.model.Conference;
import com.epam.evm.conference.model.Section;
import com.epam.evm.conference.validator.FieldUtils;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class ConferenceServiceTest {

    private final static Section SECTION = new Section(null, Long.valueOf("1"), "name");
    private final static Section SECOND_SECTION = new Section(null, Long.valueOf("2"), "name");
    private final static  List<Conference> EXPECTED_CONFERENCES = Arrays.asList(
            new Conference(Long.valueOf("1"), "name", null, Arrays.asList(SECTION, SECTION)),
            new Conference(Long.valueOf("2"), "name", null, Arrays.asList(SECOND_SECTION, SECOND_SECTION)));

    private final static String CONFERENCE_NAME = "name";
    private final static String[] SECTION_NAMES = {"name", "name"};
    private final static LocalDateTime DATE_TIME = LocalDateTime.of(2021,01, 03, 12, 00);
    private final static Conference CONFERENCE = new Conference(null, "name", DATE_TIME);


    @Test
    public void testSaveConferenceWithSectionShouldSaveConferenceWithSectionWhenTransactionIsValid() throws ServiceException, DaoException {
        //given
        DaoHelperFactory factory = mock(DaoHelperFactory.class);
        DaoHelper helper = mock(DaoHelper.class);
        when(factory.create()).thenReturn(helper);
        ConferenceDao conferenceDao = mock(ConferenceDao.class);
        SectionDao sectionDao = mock(SectionDao.class);
        when(helper.createConferenceDao()).thenReturn(conferenceDao);
        when(helper.createSectionDao()).thenReturn(sectionDao);
        when(conferenceDao.save(CONFERENCE)).thenReturn(Optional.of(Long.valueOf("1")));
        when(sectionDao.save(SECTION)).thenReturn(Optional.of(Long.valueOf("2")));
        FieldUtils validator = Mockito.mock(FieldUtils.class);
        when(validator.isValidMediumLength(CONFERENCE_NAME)).thenReturn(true);
        when(validator.isValidMediumLength(SECTION_NAMES)).thenReturn(true);
        ConferenceService service = new ConferenceService(factory,validator);
        //when
        service.saveConferenceWithSection(CONFERENCE_NAME, DATE_TIME, SECTION_NAMES);
        //then
        verify(helper).endTransaction();
    }

    @Test(expected = ServiceException.class)
    public void testSaveConferenceWithSectionShouldSaveConferenceWithSectionWhenTransactionIsInvalid() throws DaoException, ServiceException {
        //given
        DaoHelperFactory factory = mock(DaoHelperFactory.class);
        DaoHelper helper = mock(DaoHelper.class);
        when(factory.create()).thenReturn(helper);
        ConferenceDao conferenceDao = mock(ConferenceDao.class);
        SectionDao sectionDao = mock(SectionDao.class);

        when(helper.createConferenceDao()).thenReturn(conferenceDao);
        when(helper.createSectionDao()).thenReturn(sectionDao);
        when(conferenceDao.save(CONFERENCE)).thenReturn(Optional.empty());
        FieldUtils validator = Mockito.mock(FieldUtils.class);
        when(validator.isValidMediumLength(CONFERENCE_NAME)).thenReturn(true);
        when(validator.isValidMediumLength(SECTION_NAMES)).thenReturn(true);
        ConferenceService service = new ConferenceService(factory,validator);
        //when
        service.saveConferenceWithSection(CONFERENCE_NAME, DATE_TIME, SECTION_NAMES);
    }

    @Test
    public void testFindAllConferencesWithSectionsShouldReturnConferenceWithSectionWhenDataIsValid() throws DaoException, ServiceException {
        //given
        List<Section> secondSections = Arrays.asList(SECTION, SECOND_SECTION, SECTION, SECOND_SECTION);
        List<Conference> conferences = Arrays.asList(
                new Conference(Long.valueOf("1"), "name", null),
                new Conference(Long.valueOf("2"), "name", null));
        DaoHelperFactory factory = mock(DaoHelperFactory.class);
        DaoHelper helper = mock(DaoHelper.class);
        when(factory.create()).thenReturn(helper);
        ConferenceDao conferenceDao = mock(ConferenceDao.class);
        SectionDao sectionDao = mock(SectionDao.class);

        when(helper.createConferenceDao()).thenReturn(conferenceDao);
        when(helper.createSectionDao()).thenReturn(sectionDao);
        when(conferenceDao.findAll()).thenReturn(conferences);
        when(sectionDao.findAll()).thenReturn(secondSections);
        FieldUtils validator = Mockito.mock(FieldUtils.class);
        Mockito.when(validator.isValidMediumLength(anyString())).thenReturn(true);
        ConferenceService service = new ConferenceService(factory,validator);
        //when
        List<Conference> actualConferences = service.findAllConferencesWithSections();
        //then
        Assert.assertEquals(EXPECTED_CONFERENCES, actualConferences);
    }

    @Test
    public void testFindConferencesForPaginationShouldReturnConferenceWithSectionWhenDataIsValid() throws DaoException, ServiceException {
        //given
        List<Section> secondSections = Arrays.asList(SECTION, SECTION, SECOND_SECTION, SECOND_SECTION);
        List<Conference> conferences = Arrays.asList(
                new Conference(Long.valueOf("1"), "name", null),
                new Conference(Long.valueOf("2"), "name", null));
        DaoHelperFactory factory = mock(DaoHelperFactory.class);
        DaoHelper helper = mock(DaoHelper.class);
        when(factory.create()).thenReturn(helper);
        ConferenceDao conferenceDao = mock(ConferenceDao.class);
        SectionDao sectionDao = mock(SectionDao.class);
        when(helper.createConferenceDao()).thenReturn(conferenceDao);
        when(helper.createSectionDao()).thenReturn(sectionDao);

        when(conferenceDao.findEntityForPagination(2, 5)).thenReturn(conferences);
        when(sectionDao.findSectionsByConferenceId(anyLong())).thenReturn(secondSections);
        FieldUtils validator = Mockito.mock(FieldUtils.class);
        Mockito.when(validator.isValidMediumLength(anyString())).thenReturn(true);
        ConferenceService service = new ConferenceService(factory,validator);
        //when
        List<Conference> actualConferences = service.findConferencesForPagination(2,5);
        //then
        Assert.assertEquals(EXPECTED_CONFERENCES, actualConferences);
    }
}
