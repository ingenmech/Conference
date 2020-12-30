package com.epam.evm.conference.command.general;

import com.epam.evm.conference.command.Command;
import com.epam.evm.conference.command.CommandResult;
import com.epam.evm.conference.exception.ServiceException;
import com.epam.evm.conference.service.RequestService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestRemoverCommand implements Command {

    private final static String REQUEST_ID = "requestId";

    private final String page;
    private final RequestService service;

    public RequestRemoverCommand(String page, RequestService service) {
        this.page = page;
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        String idValue = request.getParameter(REQUEST_ID);
        Long id = Long.valueOf(idValue);

        service.removeRequestById(id);
        return CommandResult.forward(page);
    }
}
