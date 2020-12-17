package com.epam.evm.conference.command;

import com.epam.evm.conference.exception.ServiceException;
import com.epam.evm.conference.model.Request;
import com.epam.evm.conference.service.UpdateService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StatusRequestCommand implements Command {

    private final static String ACCEPT_REQUEST_JSP = "/controller?command=adminGoToAcceptRequest";
    private final static String REQUEST_ID = "requestId";

    private final String status;
    private final UpdateService service;

    public StatusRequestCommand(String status, UpdateService service) {
        this.status = status;
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        String idRow = request.getParameter(REQUEST_ID);
        Long id = Long.valueOf(idRow);
        Request topic = new Request(id, null, null, null, status);

        service.updateRequestStatus(topic);

        return CommandResult.forward(ACCEPT_REQUEST_JSP);
    }
}
