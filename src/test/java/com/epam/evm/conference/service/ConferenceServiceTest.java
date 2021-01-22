package com.epam.evm.conference.service;

import com.epam.evm.conference.dao.ConferencePersistentDaoImpl;
import com.epam.evm.conference.dao.SectionPersistentDaoImpl;
import com.epam.evm.conference.dao.daoInterface.ConferencePersistentDao;
import com.epam.evm.conference.dao.daoInterface.SectionPersistentDao;
import com.epam.evm.conference.dao.helper.DaoHelper;
import com.epam.evm.conference.dao.helper.DaoHelperFactory;
import com.epam.evm.conference.exception.DaoException;
import com.epam.evm.conference.exception.ServiceException;
import com.epam.evm.conference.model.Conference;
import com.epam.evm.conference.model.Section;
import com.epam.evm.conference.model.SectionStatus;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ConferenceServiceTest {

    private final static Section SECTION = new Section(Long.valueOf("1"), Long.valueOf("1"), "name", SectionStatus.ACTUAL);
    private final static Section SECOND_SECTION = new Section(Long.valueOf("2"), Long.valueOf("2"), "name", SectionStatus.ACTUAL);
    private final static List<Conference> EXPECTED_CONFERENCES = Arrays.asList(
            new Conference(Long.valueOf("1"), "name", null, Arrays.asList(SECTION, SECTION)),
            new Conference(Long.valueOf("2"), "name", null, Arrays.asList(SECOND_SECTION, SECOND_SECTION)));

    private final static String CONFERENCE_NAME = "name";
    private final static String[] SECTION_NAMES = {"name", "name"};
    private final static LocalDateTime DATE_TIME = LocalDateTime.of(2021, 1, 3, 12, 0);
    private final static Conference CONFERENCE = new Conference(null, "name", DATE_TIME);

    @Test(expected = ServiceException.class)
    public void testSaveConferenceWithSectionShouldSaveConferenceWithSectionWhenTransactionIsInvalid() throws DaoException, ServiceException {
        //given
        DaoHelperFactory factory = mock(DaoHelperFactory.class);
        DaoHelper helper = mock(DaoHelper.class);
        when(factory.create()).thenReturn(helper);
        ConferencePersistentDao conferenceDao = mock(ConferencePersistentDao.class);
        SectionPersistentDao sectionDao = mock(SectionPersistentDao.class);
        when(helper.createConferencePersistentDao()).thenReturn(conferenceDao);
        when(helper.createSectionPersistentDao()).thenReturn(sectionDao);

        when(conferenceDao.save(CONFERENCE)).thenReturn(Optional.empty());
        ConferenceService service = new ConferenceService(factory);
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
        ConferencePersistentDao conferenceDao = mock(ConferencePersistentDaoImpl.class);
        SectionPersistentDao sectionDao = mock(SectionPersistentDaoImpl.class);
        when(helper.createConferencePersistentDao()).thenReturn(conferenceDao);
        when(helper.createSectionPersistentDao()).thenReturn(sectionDao);

        when(conferenceDao.findActualConferences(any())).thenReturn(conferences);
        when(sectionDao.findActualSections()).thenReturn(secondSections);
        ConferenceService service = new ConferenceService(factory);
        //when
        List<Conference> actualConferences = service.findActualConferencesWithSections();
        //then
        Assert.assertEquals(EXPECTED_CONFERENCES, actualConferences);
    }

    @Test
    public void testFindConferencesForPaginationShouldReturnConferenceWithSectionWhenDataIsValid() throws DaoException, ServiceException {
        //given
        List<Section> secondSections = Arrays.asList(SECTION, SECOND_SECTION, SECTION, SECOND_SECTION);
        List<Conference> conferences = Arrays.asList(
                new Conference(Long.valueOf("1"), "name", null),
                new Conference(Long.valueOf("2"), "name", null));
        DaoHelperFactory factory = mock(DaoHelperFactory.class);
        DaoHelper helper = mock(DaoHelper.class);
        when(factory.create()).thenReturn(helper);
        ConferencePersistentDao conferenceDao = mock(ConferencePersistentDaoImpl.class);
        SectionPersistentDao sectionDao = mock(SectionPersistentDaoImpl.class);
        when(helper.createConferencePersistentDao()).thenReturn(conferenceDao);
        when(helper.createSectionPersistentDao()).thenReturn(sectionDao);

        when(conferenceDao.findActualConferencesForPagination(any(), anyInt(), anyInt())).thenReturn(conferences);
        when(sectionDao.findActualSections()).thenReturn(secondSections);

        ConferenceService service = new ConferenceService(factory);
        //when
        List<Conference> actualConferences = service.findActualConferencesForPagination(2, 5);
        //then
        Assert.assertEquals(EXPECTED_CONFERENCES, actualConferences);
    }

}
