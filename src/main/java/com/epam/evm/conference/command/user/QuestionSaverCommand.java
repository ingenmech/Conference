package com.epam.evm.conference.command.user;

import com.epam.evm.conference.command.Command;
import com.epam.evm.conference.command.CommandResult;
import com.epam.evm.conference.exception.FieldValidationException;
import com.epam.evm.conference.exception.ServiceException;
import com.epam.evm.conference.service.QuestionService;
import com.epam.evm.conference.validator.FieldValidator;
import com.epam.evm.conference.web.RequestContent;

public class QuestionSaverCommand implements Command {

    private final static String USER_QUESTIONS_PAGE = "/controller?command=userQuestionsPage";
    private final static String USER_ID = "userId";
    private final static String CONTENT = "content";
    private final static String REGEXP = "^\\d*$";

    private final QuestionService service;
    private final FieldValidator validator;

    public QuestionSaverCommand(QuestionService service, FieldValidator validator) {
        this.service = service;
        this.validator = validator;
    }

    @Override
    public CommandResult execute(RequestContent requestContent) throws ServiceException {

        Object rowUserId = requestContent.getSessionAttribute(USER_ID);
        if(!validator.isValid(rowUserId.toString(), REGEXP)){
            throw new FieldValidationException("Field does not match format");
        }

        Long userId = (Long)rowUserId;
        String content = requestContent.getParameter(CONTENT);

        service.saveQuestion(userId, content);
        return CommandResult.redirect(USER_QUESTIONS_PAGE);
    }
}
