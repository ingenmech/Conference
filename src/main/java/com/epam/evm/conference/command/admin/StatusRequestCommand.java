package com.epam.evm.conference.command.admin;

import com.epam.evm.conference.command.Command;
import com.epam.evm.conference.command.CommandResult;
import com.epam.evm.conference.exception.ServiceException;
import com.epam.evm.conference.model.Request;
import com.epam.evm.conference.model.RequestStatus;
import com.epam.evm.conference.service.RequestService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StatusRequestCommand implements Command {

    private final static String ACCEPT_REQUEST_JSP = "/controller?command=adminGoToAcceptRequest";
    private final static String REQUEST_ID = "requestId";
    private final static String USER_ID = "userId";
    private final static String SECTION_ID = "sectionId";
    private final static String TOPIC = "topic";

    private final RequestStatus status;
    private final RequestService service;

    public StatusRequestCommand(RequestStatus status, RequestService service) {
        this.status = status;
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        String idRow = request.getParameter(REQUEST_ID);
        Long id = Long.valueOf(idRow);

        String userIdRow = request.getParameter(USER_ID);
        Long userId = Long.valueOf(userIdRow);

        String sectionIdRow = request.getParameter(SECTION_ID);
        Long sectionId = Long.valueOf(sectionIdRow);

        String topic = request.getParameter(TOPIC);

        Request userRequest = new Request(id, sectionId, userId, topic, status);

        service.updateRequestStatus(userRequest);

        return CommandResult.forward(ACCEPT_REQUEST_JSP);
    }
}
