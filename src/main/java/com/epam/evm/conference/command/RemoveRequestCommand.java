package com.epam.evm.conference.command;

import com.epam.evm.conference.exception.ServiceException;
import com.epam.evm.conference.service.RemoveService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RemoveRequestCommand implements Command {

    private final static String REQUEST_ID = "requestId";

    private final String page;
    private final RemoveService service;

    public RemoveRequestCommand(String page, RemoveService service) {
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
