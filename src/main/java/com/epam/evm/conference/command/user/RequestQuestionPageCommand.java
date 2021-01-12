package com.epam.evm.conference.command.user;

import com.epam.evm.conference.command.Command;
import com.epam.evm.conference.command.CommandResult;
import com.epam.evm.conference.exception.ServiceException;
import com.epam.evm.conference.model.Conference;
import com.epam.evm.conference.service.ConferenceService;
import com.epam.evm.conference.web.RequestContent;

import java.util.List;

public class RequestQuestionPageCommand implements Command {

    private final static String CONFERENCE_ID = "conferenceId";
    private final static String SELECTED_CONFERENCE_ID = "selectedConferenceId";
    private final static String LIST_CONFERENCE = "conferenceList";

    private final String page;
    private final ConferenceService service;

    public RequestQuestionPageCommand(String page, ConferenceService service) {
        this.page = page;
        this.service = service;
    }

    @Override
    public CommandResult execute(RequestContent content) throws ServiceException {

        String conferenceIdRow = content.getParameter(CONFERENCE_ID);
        if (conferenceIdRow != null) {
            content.setAttribute(SELECTED_CONFERENCE_ID, conferenceIdRow);
        }
        List<Conference> conferences = service.findAllConferencesWithSections();
        content.setAttribute(LIST_CONFERENCE, conferences);
        return CommandResult.forward(page);
    }
}
