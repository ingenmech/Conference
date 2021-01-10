package com.epam.evm.conference.command.admin;

import com.epam.evm.conference.command.Command;
import com.epam.evm.conference.command.CommandResult;
import com.epam.evm.conference.web.RequestContent;

public class UpdateConferencePageCommand implements Command {

    private final static String CRATE_CONFERENCE_PAGE = "/WEB-INF/pages/create-conference-page.jsp";
    private final static int CONFERENCE_LENGTH = 150;
    private final static String CONFERENCE_LENGTH_KEY = "conferenceLength";
    private final static int SECTION_LENGTH = 150;
    private final static String SECTION_LENGTH_KEY = "sectionLength";

    @Override
    public CommandResult execute(RequestContent content) {

        content.setAttribute(CONFERENCE_LENGTH_KEY, CONFERENCE_LENGTH);
        content.setAttribute(SECTION_LENGTH_KEY, SECTION_LENGTH);

        return CommandResult.forward(CRATE_CONFERENCE_PAGE);
    }

}
