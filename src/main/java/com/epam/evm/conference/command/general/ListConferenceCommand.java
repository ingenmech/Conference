package com.epam.evm.conference.command.general;

import com.epam.evm.conference.command.Command;
import com.epam.evm.conference.exception.ServiceException;
import com.epam.evm.conference.model.Conference;
import com.epam.evm.conference.service.FindService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ListConferenceCommand  extends AbstractPageIteratorCommand<Conference> implements Command {

    private final static String LIST_CONFERENCE_PAGE = "WEB-INF/pages/list-conference-page.jsp";
    private final static String CONFERENCE_LIST = "conferenceList";
    private final static int ELEMENTS_NUMBER_ON_PAGE = 2;

    private final FindService service;

    public ListConferenceCommand(FindService service) {
        super(LIST_CONFERENCE_PAGE, CONFERENCE_LIST, ELEMENTS_NUMBER_ON_PAGE);
        this.service = service;
    }

    @Override
    public List<Conference> createService(HttpServletRequest request, int offset) throws ServiceException {
        return service.findConferencesWithLimit(ELEMENTS_NUMBER_ON_PAGE, offset);
    }

//    @Override
//    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
//
//        String pageNumberRow = request.getParameter(PAGE_NUMBER);
//        int pageNumber = (pageNumberRow != null) ? Integer.parseInt(pageNumberRow) : 0;
//        if (pageNumber < 0) {
//            pageNumber = 0;
//        }
//        String direction = request.getParameter(DIRECTION);
//        if (PREVIOUS.equals(direction) && pageNumber != 0){
//            pageNumber-=2;
//        }
//        int offset = pageNumber * ELEMENTS_NUMBER_ON_PAGE;
//        List<Conference> conferences = service.findConferencesWithLimit(ELEMENTS_NUMBER_ON_PAGE, offset);
//        if (!conferences.isEmpty()) {
//            request.setAttribute(CONFERENCE_LIST, conferences);
//            pageNumber++;
//        }
//
//        request.setAttribute(PAGE_NUMBER, pageNumber);
//        request.setAttribute(ELEMENTS_NUMBER, ELEMENTS_NUMBER_ON_PAGE);
//
//        return CommandResult.forward(LIST_CONFERENCE_PAGE);
//    }


}
