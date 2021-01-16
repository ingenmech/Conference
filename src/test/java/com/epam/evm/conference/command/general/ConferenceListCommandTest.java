package com.epam.evm.conference.command.general;

import com.epam.evm.conference.command.AbstractPaginationCommandTest;
import com.epam.evm.conference.command.Command;
import com.epam.evm.conference.exception.ServiceException;
import com.epam.evm.conference.model.Conference;
import com.epam.evm.conference.service.ConferenceService;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;

public class ConferenceListCommandTest extends AbstractPaginationCommandTest<Conference> {

    private final static String CONFERENCE_PAGE = "WEB-INF/pages/list-conference-page.jsp";
    private final static String CONFERENCE_LIST = "conferenceList";
    private final static List<Conference> CONFERENCES = Arrays.asList(
            new Conference(null, "name", null, null),
            new Conference(null, "name", null, null));

    public ConferenceListCommandTest() {
        super(CONFERENCES, CONFERENCE_LIST, CONFERENCE_PAGE);
    }


    @Override
    protected Command createCommand() throws ServiceException {
        ConferenceService service = Mockito.mock(ConferenceService.class);
        Mockito.when(service.findConferencesForPagination(anyInt(), anyInt())).thenReturn(CONFERENCES);
        Mockito.when(service.countRows()).thenReturn(Long.valueOf("11"));
        return new ConferenceListCommand(service);

    }
}
