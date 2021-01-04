package com.epam.evm.conference.command.admin;

import com.epam.evm.conference.command.Command;
import com.epam.evm.conference.command.general.AbstractPaginationCommand;
import com.epam.evm.conference.exception.ServiceException;
import com.epam.evm.conference.model.Question;
import com.epam.evm.conference.service.QuestionService;
import com.epam.evm.conference.web.RequestContent;

import java.util.List;

public class AdminQuestionPageCommand extends AbstractPaginationCommand<Question> implements Command {

    private final static String ADMIN_QUESTION_PAGE = "/WEB-INF/pages/questions-page.jsp";
    private final static String ADMIN_QUESTION_LIST = "questionsList";
    private final static int ELEMENTS_LIMIT = 5;
    private final QuestionService service;

    public AdminQuestionPageCommand(QuestionService service) {
        super(ADMIN_QUESTION_PAGE, ADMIN_QUESTION_LIST, ELEMENTS_LIMIT);
        this.service=service;
    }

    @Override
    public List<Question> createService(RequestContent content, int offset) throws ServiceException {
        return service.findAllQuestionWithUserLogin(ELEMENTS_LIMIT, offset);
    }
}
