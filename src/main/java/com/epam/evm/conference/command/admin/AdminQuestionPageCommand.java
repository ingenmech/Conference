package com.epam.evm.conference.command.admin;

import com.epam.evm.conference.command.Command;
import com.epam.evm.conference.command.CommandResult;
import com.epam.evm.conference.exception.ServiceException;
import com.epam.evm.conference.model.Question;
import com.epam.evm.conference.service.FindService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AdminQuestionPageCommand implements Command {

    private final static String ADMIN_QUESTION_PAGE = "/WEB-INF/pages/questions-page.jsp";
    private final static String ADMIN_QUESTION_LIST = "questionsList";
    private final FindService service;

    public AdminQuestionPageCommand(FindService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        List<Question> questions =service.findAllQuestionWithUserLogin();
        request.setAttribute(ADMIN_QUESTION_LIST, questions);

        return CommandResult.forward(ADMIN_QUESTION_PAGE);
    }
}
