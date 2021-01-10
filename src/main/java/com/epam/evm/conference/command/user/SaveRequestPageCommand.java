package com.epam.evm.conference.command.user;

import com.epam.evm.conference.command.Command;
import com.epam.evm.conference.command.CommandResult;
import com.epam.evm.conference.exception.ServiceException;
import com.epam.evm.conference.model.Conference;
import com.epam.evm.conference.service.ConferenceService;
import com.epam.evm.conference.web.RequestContent;

import java.util.List;

public class SaveRequestPageCommand implements Command {

    private final static String CREATE_REQUEST_JSP = "/WEB-INF/pages/send-request-page.jsp";
    private final static String LIST_CONFERENCE = "conferenceList";
    private final static int TOPIC_LENGTH = 150;
    private final static String TOPIC_LENGTH_KEY = "topicLength";

    private final ConferenceService service;

    public SaveRequestPageCommand(ConferenceService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(RequestContent content) throws ServiceException {

        content.setAttribute(TOPIC_LENGTH_KEY, TOPIC_LENGTH);

        List<Conference> conferences = service.findAllConferencesWithSections();
        content.setAttribute(LIST_CONFERENCE, conferences);

        return CommandResult.forward(CREATE_REQUEST_JSP);
    }

}
