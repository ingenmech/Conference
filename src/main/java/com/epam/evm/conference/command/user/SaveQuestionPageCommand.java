package com.epam.evm.conference.command.user;

import com.epam.evm.conference.command.Command;
import com.epam.evm.conference.command.CommandResult;
import com.epam.evm.conference.web.RequestContent;

public class SaveQuestionPageCommand implements Command {

    private final static String CRATE_QUESTION_PAGE = "/WEB-INF/pages/create-question-page.jsp";
    private final static int LENGTH = 150;
    private final static String QUESTION_LENGTH = "questionLength";

    @Override
    public CommandResult execute(RequestContent content) {

        content.setAttribute(QUESTION_LENGTH, LENGTH);

        return CommandResult.forward(CRATE_QUESTION_PAGE);
    }
}
