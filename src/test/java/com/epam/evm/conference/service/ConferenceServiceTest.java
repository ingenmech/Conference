package com.epam.evm.conference.service;

import com.epam.evm.conference.model.Conference;
import com.epam.evm.conference.model.Section;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class ConferenceServiceTest {

    private final static Section SECTION = new Section(null, Long.valueOf("1"), "name");
    private final static Section SECOND_SECTION = new Section(null, Long.valueOf("2"), "name");
    private final static List<Section> SECTIONS = Arrays.asList(SECTION, SECTION);
    private final static Conference CONFERENCE = new Conference(Long.valueOf("1"), "name", null, SECTIONS);
    private final static  List<Conference> EXPECTED_CONFERENCES = Arrays.asList(
            new Conference(Long.valueOf("1"), "name", null, Arrays.asList(SECTION, SECTION)),
            new Conference(Long.valueOf("2"), "name", null, Arrays.asList(SECOND_SECTION, SECOND_SECTION)));

    private final static String conferenceName = "name";
    private final static String[] sectionNames = {"name", "name"};
    private final static LocalDateTime dateTime = LocalDateTime.of(2021,01, 03, 12, 00);

//    @Test
//    public void testSaveConferenceWithSectionShouldSaveConferenceWithSectionWhenTransactionIsValid() throws DaoException, ServiceException {
//        //given
//        DaoHelperFactory factory = mock(DaoHelperFactory.class);
//        DaoHelper helper = mock(DaoHelper.class);
//        when(factory.create()).thenReturn(helper);
//        ConferenceDao conferenceDao = mock(ConferenceDao.class);
//        SectionDao sectionDao = mock(SectionDao.class);
//
//        when(helper.createConferenceDao()).thenReturn(conferenceDao);
//        when(helper.createSectionDao()).thenReturn(sectionDao);
//        when(conferenceDao.save(CONFERENCE)).thenReturn(Optional.of(Long.valueOf("1")));
//        when(sectionDao.save(SECTION)).thenReturn(Optional.of(Long.valueOf("2")));
//        FieldValidator validator = Mockito.mock(FieldValidator.class);
//        when(validator.isValid(conferenceName, "^.{1,150}$")).thenReturn(true);
//        when(validator.isValid(sectionNames, "^.{1,150}$")).thenReturn(true);
//        ConferenceService service = new ConferenceService(factory,validator);
//        //when
//        service.saveConferenceWithSection(conferenceName, null, sectionNames);
//        //then
//        verify(helper).endTransaction();
//    }
//
//    @Test(expected = ServiceException.class)
//    public void testSaveConferenceWithSectionShouldSaveConferenceWithSectionWhenTransactionIsInvalid() throws DaoException, ServiceException {
//        //given
//        DaoHelperFactory factory = mock(DaoHelperFactory.class);
//        DaoHelper helper = mock(DaoHelper.class);
//        when(factory.create()).thenReturn(helper);
//        ConferenceDao conferenceDao = mock(ConferenceDao.class);
//        SectionDao sectionDao = mock(SectionDao.class);
//
//        when(helper.createConferenceDao()).thenReturn(conferenceDao);
//        when(helper.createSectionDao()).thenReturn(sectionDao);
//        when(conferenceDao.save(CONFERENCE)).thenReturn(Optional.empty());
//        FieldValidator validator = Mockito.mock(FieldValidator.class);
//        when(validator.isValid(conferenceName,"^.{1,150}$")).thenReturn(true);
//        when(validator.isValid(sectionNames, "^.{1,150}$")).thenReturn(true);
//        ConferenceService service = new ConferenceService(factory,validator);
//        //when
//        service.saveConferenceWithSection(conferenceName, dateTime, sectionNames);
//    }
//
//    @Test
//    public void testFindAllConferencesWithSectionsShouldReturnConferenceWithSectionWhenDataIsValid() throws DaoException, ServiceException {
//        //given
//        List<Section> secondSections = Arrays.asList(SECTION, SECOND_SECTION, SECTION, SECOND_SECTION);
//        List<Conference> conferences = Arrays.asList(
//                new Conference(Long.valueOf("1"), "name", null),
//                new Conference(Long.valueOf("2"), "name", null));
//        DaoHelperFactory factory = mock(DaoHelperFactory.class);
//        DaoHelper helper = mock(DaoHelper.class);
//        when(factory.create()).thenReturn(helper);
//        ConferenceDao conferenceDao = mock(ConferenceDao.class);
//        SectionDao sectionDao = mock(SectionDao.class);
//
//        when(helper.createConferenceDao()).thenReturn(conferenceDao);
//        when(helper.createSectionDao()).thenReturn(sectionDao);
//        when(conferenceDao.getAll()).thenReturn(conferences);
//        when(sectionDao.getAll()).thenReturn(secondSections);
//        FieldValidator validator = Mockito.mock(FieldValidator.class);
//        ConferenceService service = new ConferenceService(factory,validator);
//        //when
//        List<Conference> actualConferences = service.findAllConferencesWithSections();
//        //then
//        Assert.assertEquals(EXPECTED_CONFERENCES, actualConferences);
//    }
//
//    @Test
//    public void testFindConferencesForPaginationShouldReturnConferenceWithSectionWhenDataIsValid() throws DaoException, ServiceException {
//        //given
//        List<Section> secondSections = Arrays.asList(SECTION, SECTION, SECOND_SECTION, SECOND_SECTION);
//        List<Conference> conferences = Arrays.asList(
//                new Conference(Long.valueOf("1"), "name", null),
//                new Conference(Long.valueOf("2"), "name", null));
//        DaoHelperFactory factory = mock(DaoHelperFactory.class);
//        DaoHelper helper = mock(DaoHelper.class);
//        when(factory.create()).thenReturn(helper);
//        ConferenceDao conferenceDao = mock(ConferenceDao.class);
//        SectionDao sectionDao = mock(SectionDao.class);
//        when(helper.createConferenceDao()).thenReturn(conferenceDao);
//        when(helper.createSectionDao()).thenReturn(sectionDao);
//
//        when(conferenceDao.findEntityForPagination(2, 5)).thenReturn(conferences);
//        when(sectionDao.findSectionsByConferenceId(anyLong())).thenReturn(secondSections);//thenAnswer(invocation -> secondSections);
//        FieldValidator validator = Mockito.mock(FieldValidator.class);
//        ConferenceService service = new ConferenceService(factory,validator);
//        //when
//        List<Conference> actualConferences = service.findConferencesForPagination(2,5);
//        //then
//        Assert.assertEquals(EXPECTED_CONFERENCES, actualConferences);
//    }
}
