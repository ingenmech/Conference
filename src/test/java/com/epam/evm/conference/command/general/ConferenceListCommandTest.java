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

public class ConferenceListCommandTest extends AbstractPaginationCommandTest {

//    @Override
//    protected Command createCommand() throws ServiceException {
//        List<Conference> conferences = Arrays.asList(
//                new Conference(null, "name", null, null),
//                new Conference(null, "name", null, null));
//        ConferenceService service = Mockito.mock(ConferenceService.class);
//        Mockito.when(service.findConferencesForPagination(anyInt(), anyInt())).thenReturn(conferences);
//        return new ConferenceListCommand(service);
//
//    }
}
