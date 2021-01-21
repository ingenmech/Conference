package com.epam.evm.conference.command.general;

import com.epam.evm.conference.command.Command;
import com.epam.evm.conference.command.CommandResult;
import com.epam.evm.conference.exception.FieldValidationException;
import com.epam.evm.conference.exception.ServiceException;
import com.epam.evm.conference.service.MessageService;
import com.epam.evm.conference.utils.NumberUtils;
import com.epam.evm.conference.web.RequestContent;

public class SaveMessageCommand implements Command {

    private final static String USER_ID = "userId";
    private final static String QUESTION_ID = "questionId";
    private final static String CONTENT = "content";
    private final static String QUESTION_CONTENT = "questionContent";
    private final static String ALL_USERS_MESSAGE_PAGE = "/controller?command=allUsersMessagePage";

    private final MessageService service;

    public SaveMessageCommand(MessageService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(RequestContent requestContent) throws ServiceException {

        Object userIdRow = requestContent.getSessionAttribute(USER_ID);
        String questionIdRow = requestContent.getParameter(QUESTION_ID);
        if(!NumberUtils.isValidDigit(userIdRow.toString())){
            throw new FieldValidationException("Field user id does not match format");
        }
        if(!NumberUtils.isValidDigit(questionIdRow)){
            throw new FieldValidationException("Field question id does not match format");
        }
        Long userId = (Long) requestContent.getSessionAttribute(USER_ID);
        Long questionId = Long.parseLong(questionIdRow);
        String content = requestContent.getParameter(CONTENT);

        service.saveMessage(questionId, userId, content);

        String questionContent = requestContent.getParameter(QUESTION_CONTENT);
        requestContent.setAttribute(QUESTION_CONTENT, questionContent);
        requestContent.setAttribute(QUESTION_ID, questionId);
        return CommandResult.forward(ALL_USERS_MESSAGE_PAGE);
    }
}
