package com.epam.evm.conference.command.admin;

import com.epam.evm.conference.command.Command;
import com.epam.evm.conference.command.general.AbstractPaginationCommand;
import com.epam.evm.conference.exception.ServiceException;
import com.epam.evm.conference.model.Question;
import com.epam.evm.conference.model.QuestionDto;
import com.epam.evm.conference.service.QuestionService;
import com.epam.evm.conference.web.RequestContent;

import java.util.List;

public class AdminQuestionPageCommand extends AbstractPaginationCommand<QuestionDto> implements Command {

    private final static String ADMIN_QUESTION_PAGE = "/WEB-INF/pages/questions-page.jsp";
    private final static String ADMIN_QUESTION_LIST = "questionsList";
    private final QuestionService service;

    public AdminQuestionPageCommand(QuestionService service) {
        super(ADMIN_QUESTION_PAGE, ADMIN_QUESTION_LIST);
        this.service=service;
    }

    @Override
    public List<QuestionDto> createService(RequestContent content, int limit, int offset) throws ServiceException {
        return service.findAllQuestionWithUserLogin(limit, offset);
    }

    @Override
    public Long countRows() throws ServiceException {
        return service.countRows();
    }
}
