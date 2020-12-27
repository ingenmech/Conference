package com.epam.evm.conference.command.user;

import com.epam.evm.conference.command.Command;
import com.epam.evm.conference.command.CommandResult;
import com.epam.evm.conference.exception.ServiceException;
import com.epam.evm.conference.model.Conference;
import com.epam.evm.conference.service.FindService;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class RequestPageCommand implements Command {

    private final static String CREATE_REQUEST_JSP = "/WEB-INF/pages/send-request-page.jsp";
    private final static String LIST_CONFERENCE = "conferenceList";
    private final static String MAP_SECTION = "sectionList";
    private final FindService service;

    public RequestPageCommand(FindService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        List<Conference> conferences = service.findAllConferencesWithSections();
        request.setAttribute(LIST_CONFERENCE, conferences);

        Gson gson = new Gson();
        String conferenceJson = gson.toJson(conferences);
        request.setAttribute(MAP_SECTION, conferenceJson);

        return CommandResult.forward(CREATE_REQUEST_JSP);
    }

}
