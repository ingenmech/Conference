package com.epam.evm.conference.command.general;

import com.epam.evm.conference.command.Command;
import com.epam.evm.conference.command.CommandResult;
import com.epam.evm.conference.exception.ServiceException;
import com.epam.evm.conference.service.RequestService;
import com.epam.evm.conference.web.RequestContent;

public class RequestRemoverCommand implements Command {

    private final static String REQUEST_ID = "requestId";

    private final String page;
    private final RequestService service;

    public RequestRemoverCommand(String page, RequestService service) {
        this.page = page;
        this.service = service;
    }

    @Override
    public CommandResult execute(RequestContent content) throws ServiceException {

        String idValue = content.getParameter(REQUEST_ID);
        Long id = Long.valueOf(idValue);

        service.removeRequestById(id);
        return CommandResult.forward(page);
    }
}
