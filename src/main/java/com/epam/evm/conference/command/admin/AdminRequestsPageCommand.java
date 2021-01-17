package com.epam.evm.conference.command.admin;

import com.epam.evm.conference.command.Command;
import com.epam.evm.conference.command.general.AbstractPaginationCommand;
import com.epam.evm.conference.exception.ServiceException;
import com.epam.evm.conference.model.Request;
import com.epam.evm.conference.service.RequestService;
import com.epam.evm.conference.web.RequestContent;

import java.util.List;

public class AdminRequestsPageCommand extends AbstractPaginationCommand<Request> implements Command {

    private final static String GO_TO_SENT_REQUESTS = "/WEB-INF/pages/accept-request-page.jsp";
    private final static String REQUEST_LIST = "requestList";
    private final RequestService service;

    public AdminRequestsPageCommand(RequestService service) {
        super(GO_TO_SENT_REQUESTS, REQUEST_LIST);
        this.service = service;

    }

    @Override
    public List<Request> createService(RequestContent content, int limit, int offset) throws ServiceException {
        return service.findAllRequestsWithUsersSectionsConferences(limit, offset);
    }

    @Override
    public Long countRows() throws ServiceException {
        return service.countRows();
    }
}
