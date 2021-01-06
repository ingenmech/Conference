package com.epam.evm.conference.service;

import com.epam.evm.conference.dao.daoInterface.ConferenceDao;
import com.epam.evm.conference.dao.daoInterface.SectionDao;
import com.epam.evm.conference.dao.helper.DaoHelper;
import com.epam.evm.conference.dao.helper.DaoHelperFactory;
import com.epam.evm.conference.exception.DaoException;
import com.epam.evm.conference.exception.ServiceException;
import com.epam.evm.conference.model.Conference;
import com.epam.evm.conference.model.Section;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class ConferenceServiceTest {

    private final static Section SECTION = new Section(null, Long.valueOf("1"), "name");
    private final static Section SECOND_SECTION = new Section(null, Long.valueOf("2"), "name");
    private final static List<Section> SECTIONS = Arrays.asList(SECTION, SECTION);
    private final static Conference CONFERENCE = new Conference(Long.valueOf("1"), "name", null, SECTIONS);
    private final static  List<Conference> EXPECTED_CONFERENCES = Arrays.asList(
            new Conference(Long.valueOf("1"), "name", null, Arrays.asList(SECTION, SECTION)),
            new Conference(Long.valueOf("2"), "name", null, Arrays.asList(SECOND_SECTION, SECOND_SECTION)));
    private final static List<Conference> CONFERENCES = Arrays.asList(
            new Conference(Long.valueOf("1"), "name", null),
            new Conference(Long.valueOf("2"), "name", null));

    @Test
    public void testSaveConferenceWithSectionShouldSaveConferenceWithSectionWhenTransactionIsValid() throws DaoException, ServiceException {
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
        ConferenceService service = new ConferenceService(factory);
        //when
        service.saveConferenceWithSection(CONFERENCE);
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
        ConferenceService service = new ConferenceService(factory);
        //when
        service.saveConferenceWithSection(CONFERENCE);
    }

    @Test
    public void testFindAllConferencesWithSectionsShouldReturnConferenceWithSectionWhenDataIsValid() throws DaoException, ServiceException {
        //given
        List<Section> secondSections = Arrays.asList(SECTION, SECOND_SECTION, SECTION, SECOND_SECTION);
        DaoHelperFactory factory = mock(DaoHelperFactory.class);
        DaoHelper helper = mock(DaoHelper.class);
        when(factory.create()).thenReturn(helper);
        ConferenceDao conferenceDao = mock(ConferenceDao.class);
        SectionDao sectionDao = mock(SectionDao.class);

        when(helper.createConferenceDao()).thenReturn(conferenceDao);
        when(helper.createSectionDao()).thenReturn(sectionDao);
        when(conferenceDao.getAll()).thenReturn(CONFERENCES);
        when(sectionDao.getAll()).thenReturn(secondSections);

        ConferenceService service = new ConferenceService(factory);
        //when
        List<Conference> actualConferences = service.findAllConferencesWithSections();
        //then
        Assert.assertEquals(EXPECTED_CONFERENCES, actualConferences);
    }

    @Test
    public void testFindConferencesForPaginationShouldReturnConferenceWithSectionWhenDataIsValid() throws DaoException, ServiceException {
        //given
        List<Section> secondSections = Arrays.asList(SECTION, SECTION, SECOND_SECTION, SECOND_SECTION);
        DaoHelperFactory factory = mock(DaoHelperFactory.class);
        DaoHelper helper = mock(DaoHelper.class);
        when(factory.create()).thenReturn(helper);
        ConferenceDao conferenceDao = mock(ConferenceDao.class);
        SectionDao sectionDao = mock(SectionDao.class);

        when(helper.createConferenceDao()).thenReturn(conferenceDao);
        when(helper.createSectionDao()).thenReturn(sectionDao);
        when(conferenceDao.findEntityForPagination(2, 5)).thenReturn(CONFERENCES);
        when(sectionDao.findSectionsByConferenceId(anyLong())).thenAnswer(invocation -> secondSections);

        ConferenceService service = new ConferenceService(factory);
        //when
        List<Conference> actualConferences = service.findConferencesForPagination(2,5);
        //then
        Assert.assertEquals(EXPECTED_CONFERENCES, actualConferences);
    }
}
