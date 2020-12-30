package com.epam.evm.conference.command.user;

import com.epam.evm.conference.command.Command;
import com.epam.evm.conference.command.CommandResult;
import com.epam.evm.conference.exception.ServiceException;
import com.epam.evm.conference.model.Conference;
import com.epam.evm.conference.service.ConferenceService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class RequestSaverPageCommand implements Command {

    private final static String CREATE_REQUEST_JSP = "/WEB-INF/pages/send-request-page.jsp";
    private final static String LIST_CONFERENCE = "conferenceList";
    private final static int TOPIC_LENGTH = 150;
    private final static String TOPIC_LENGTH_KEY = "topicLength";

    private final ConferenceService service;

    public RequestSaverPageCommand(ConferenceService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        request.setAttribute(TOPIC_LENGTH_KEY, TOPIC_LENGTH);

        List<Conference> conferences = service.findAllConferencesWithSections();
        request.setAttribute(LIST_CONFERENCE, conferences);

        return CommandResult.forward(CREATE_REQUEST_JSP);
    }

}
