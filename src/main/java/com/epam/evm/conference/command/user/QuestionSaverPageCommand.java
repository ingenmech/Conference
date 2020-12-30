package com.epam.evm.conference.command.user;

import com.epam.evm.conference.command.Command;
import com.epam.evm.conference.command.CommandResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QuestionSaverPageCommand implements Command {

    private final static String CRATE_QUESTION_PAGE = "/WEB-INF/pages/create-question-page.jsp";
    private final static int LENGTH = 150;
    private final static String QUESTION_LENGTH = "questionLength";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {

        request.setAttribute(QUESTION_LENGTH, LENGTH);

        return CommandResult.forward(CRATE_QUESTION_PAGE);
    }
}
