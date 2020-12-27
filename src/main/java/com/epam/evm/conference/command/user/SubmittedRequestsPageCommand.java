package com.epam.evm.conference.command.user;

import com.epam.evm.conference.command.Command;
import com.epam.evm.conference.command.CommandResult;
import com.epam.evm.conference.exception.ServiceException;
import com.epam.evm.conference.model.Request;
import com.epam.evm.conference.service.FindService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class SubmittedRequestsPageCommand implements Command {

    private final static String GO_TO_SENT_REQUESTS = "/WEB-INF/pages/sent-requests-page.jsp";
    private final static String USER_ID = "userId";
    private final static String REQUEST_LIST = "userRequestList";

    private final FindService service;

    public SubmittedRequestsPageCommand(FindService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        HttpSession session = request.getSession();
        Long userId = (Long)session.getAttribute(USER_ID);

        List<Request> requests = service.findAllRequestsByUserId(userId);
        request.setAttribute(REQUEST_LIST, requests);

        return CommandResult.forward(GO_TO_SENT_REQUESTS);
    }
}
