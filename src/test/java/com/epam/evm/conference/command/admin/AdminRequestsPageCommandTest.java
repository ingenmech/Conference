package com.epam.evm.conference.command.admin;

import com.epam.evm.conference.command.Command;
import com.epam.evm.conference.exception.ServiceException;
import com.epam.evm.conference.command.AbstractPaginationCommandTest;
import com.epam.evm.conference.model.Request;
import com.epam.evm.conference.service.RequestService;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;

public class AdminRequestsPageCommandTest extends AbstractPaginationCommandTest {

//    @Override
//    protected Command createCommand() throws ServiceException {
//        List<Request> list = Arrays.asList(
//                new Request(null, null, null, null, null),
//                new Request(null, null, null, null, null));
//        RequestService service = Mockito.mock(RequestService.class);
//        Mockito.when(service.findAllRequestsWithUsersSectionsConferences(anyInt(),anyInt())).thenReturn(list);
//        return new AdminRequestsPageCommand(service);
//    }
}
