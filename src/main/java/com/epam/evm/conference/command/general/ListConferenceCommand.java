package com.epam.evm.conference.command.general;

import com.epam.evm.conference.command.Command;
import com.epam.evm.conference.exception.ServiceException;
import com.epam.evm.conference.model.Conference;
import com.epam.evm.conference.service.ConferenceService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ListConferenceCommand  extends AbstractPaginationCommand<Conference> implements Command {

    private final static String LIST_CONFERENCE_PAGE = "WEB-INF/pages/list-conference-page.jsp";
    private final static String CONFERENCE_LIST = "conferenceList";
    private final static int ELEMENTS_NUMBER_ON_PAGE = 4;

    private final ConferenceService service;

    public ListConferenceCommand(ConferenceService service) {
        super(LIST_CONFERENCE_PAGE, CONFERENCE_LIST, ELEMENTS_NUMBER_ON_PAGE);
        this.service = service;
    }

    @Override
    public List<Conference> createService(HttpServletRequest request, int offset) throws ServiceException {
        return service.findConferencesWithLimit(ELEMENTS_NUMBER_ON_PAGE, offset);
    }

}
