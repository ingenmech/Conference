package com.epam.evm.conference.command.user;

import com.epam.evm.conference.command.Command;
import com.epam.evm.conference.command.CommandResult;
import com.epam.evm.conference.exception.ServiceException;
import com.epam.evm.conference.model.Question;
import com.epam.evm.conference.service.QuestionService;
import com.epam.evm.conference.web.RequestContent;

public class QuestionSaverCommand implements Command {

    private final static String USER_QUESTIONS_PAGE = "/controller?command=userQuestionsPage";
    private final static String USER_ID = "userId";
    private final static String CONTENT = "content";

    private final QuestionService service;

    public QuestionSaverCommand(QuestionService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(RequestContent requestContent) throws ServiceException {

        Long userId = (Long) requestContent.getSessionAttribute(USER_ID);
        String content = requestContent.getParameter(CONTENT);

        Question question = new Question(null, userId, content);
        service.saveQuestion(question);

        return CommandResult.redirect(USER_QUESTIONS_PAGE);
    }
}
