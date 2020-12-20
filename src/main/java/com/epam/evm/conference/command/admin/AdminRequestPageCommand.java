package com.epam.evm.conference.command.admin;

import com.epam.evm.conference.command.Command;
import com.epam.evm.conference.command.CommandResult;
import com.epam.evm.conference.exception.ServiceException;
import com.epam.evm.conference.model.Request;
import com.epam.evm.conference.service.FindService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AdminRequestPageCommand implements Command {

    private final static String GO_TO_SENT_REQUESTS = "/WEB-INF/pages/accept-request-page.jsp";
    private final static String REQUEST_LIST = "requestList";

    private final FindService service;

    public AdminRequestPageCommand(FindService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        List<Request> requests = service.findAllRequestsWithUsersSectionsConferences();
        request.setAttribute(REQUEST_LIST, requests);

        return CommandResult.forward(GO_TO_SENT_REQUESTS);
    }
}
