package com.epam.evm.conference.command.general;

import com.epam.evm.conference.command.Command;
import com.epam.evm.conference.exception.ServiceException;
import com.epam.evm.conference.model.Conference;
import com.epam.evm.conference.service.ConferenceService;
import com.epam.evm.conference.web.RequestContent;

import java.util.List;

public class ConferenceListCommand extends AbstractPaginationCommand<Conference> implements Command {

    private final static String LIST_CONFERENCE_PAGE = "WEB-INF/pages/list-conference-page.jsp";
    private final static String CONFERENCE_LIST = "conferenceList";
    private final static int PAGE_SIZE = 7;
    private final ConferenceService service;

    public ConferenceListCommand(ConferenceService service) {
        super(LIST_CONFERENCE_PAGE, CONFERENCE_LIST, PAGE_SIZE);
        this.service = service;
    }

    @Override
    public List<Conference> createService(RequestContent content,int limit, int offset) throws ServiceException {
        return service.findConferencesForPagination(limit, offset);
    }

    @Override
    public Long countRows() throws ServiceException {
        return service.countRows();
    }

}
