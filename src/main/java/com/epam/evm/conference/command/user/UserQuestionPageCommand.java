package com.epam.evm.conference.command.user;

import com.epam.evm.conference.command.Command;
import com.epam.evm.conference.command.CommandResult;
import com.epam.evm.conference.exception.ServiceException;
import com.epam.evm.conference.model.Question;
import com.epam.evm.conference.service.QuestionService;
import com.epam.evm.conference.web.RequestContent;

import java.util.List;

public class UserQuestionPageCommand implements Command {

    private final static String ADMIN_QUESTION_PAGE = "/WEB-INF/pages/questions-page.jsp";
    private final static String USER_QUESTION_LIST = "questionsList";
    private final static String USER_ID = "userId";

    private final QuestionService service;

    public UserQuestionPageCommand(QuestionService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(RequestContent content) throws ServiceException {

        Long userId = (Long) content.getSessionAttribute(USER_ID);

        List<Question> questions =service.findQuestionsByUserId(userId);
        content.setAttribute(USER_QUESTION_LIST, questions);

        return CommandResult.forward(ADMIN_QUESTION_PAGE);
    }
}
