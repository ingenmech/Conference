package com.epam.evm.conference.command.admin;

import com.epam.evm.conference.command.Command;
import com.epam.evm.conference.exception.ServiceException;
import com.epam.evm.conference.command.AbstractPaginationCommandTest;
import com.epam.evm.conference.model.RequestDto;
import com.epam.evm.conference.service.RequestService;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;

public class AdminRequestsPageCommandTest extends AbstractPaginationCommandTest<RequestDto> {

    private final static List<RequestDto> LIST = Arrays.asList(
            new RequestDto(null, null, null, null, null, null,
                    null, null, null, null),
            new RequestDto(null, null, null, null, null, null,
                    null, null, null, null));
    private final static String REQUEST_LIST = "requestList";

    public AdminRequestsPageCommandTest() {
        super(LIST, REQUEST_LIST);
    }

    @Override
    protected Command createCommand() throws ServiceException {
        RequestService service = Mockito.mock(RequestService.class);
        Mockito.when(service.findActualRequestsWithUsersSectionsConferences(anyInt(), anyInt())).thenReturn(LIST);
        Mockito.when(service.countRows()).thenReturn(Long.valueOf("11"));
        return new AdminRequestsPageCommand(service);
    }
}
