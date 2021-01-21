package com.epam.evm.conference.command.user;

import com.epam.evm.conference.command.Command;
import com.epam.evm.conference.command.CommandResult;
import com.epam.evm.conference.exception.ServiceException;
import com.epam.evm.conference.model.RequestDto;
import com.epam.evm.conference.service.RequestService;
import com.epam.evm.conference.web.RequestContent;

import java.util.List;

public class UserRequestsPageCommand implements Command {

    private final static String GO_TO_SENT_REQUESTS = "/WEB-INF/pages/sent-requests-page.jsp";
    private final static String USER_ID = "userId";
    private final static String REQUEST_LIST = "userRequestList";

    private final RequestService service;

    public UserRequestsPageCommand(RequestService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(RequestContent content) throws ServiceException {

        Long userId = (Long)content.getSessionAttribute(USER_ID);
        List<RequestDto> requests = service.findAllRequestsByUserId(userId);
        content.setAttribute(REQUEST_LIST, requests);
        return CommandResult.forward(GO_TO_SENT_REQUESTS);
    }
}
