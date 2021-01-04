package com.epam.evm.conference.command.general;

import com.epam.evm.conference.command.Command;
import com.epam.evm.conference.command.CommandResult;
import com.epam.evm.conference.exception.ServiceException;
import com.epam.evm.conference.model.Message;
import com.epam.evm.conference.service.MessageService;
import com.epam.evm.conference.web.RequestContent;

import java.time.LocalDateTime;

public class MessageSaverCommand implements Command {

    private final static String USER_ID = "userId";
    private final static String QUESTION_ID = "questionId";
    private final static String CONTENT = "content";
    private final static String QUESTION_CONTENT = "questionContent";
    private final static String ALL_USERS_MESSAGE_PAGE = "/controller?command=allUsersMessagePage";
    private final MessageService service;

    public MessageSaverCommand(MessageService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(RequestContent requestContent) throws ServiceException {

        Long userId = (Long) requestContent.getSessionAttribute(USER_ID);
        String questionIdRow = requestContent.getParameter(QUESTION_ID);
        Long questionId = Long.valueOf(questionIdRow);
        String content = requestContent.getParameter(CONTENT);
        LocalDateTime dateTime = LocalDateTime.now();

        Message message = new Message(null, questionId, userId, dateTime, content);
        service.saveMessage(message);

        String questionContent = requestContent.getParameter(QUESTION_CONTENT);
        requestContent.setAttribute(QUESTION_CONTENT, questionContent);
        requestContent.setAttribute(QUESTION_ID, questionId);
        return CommandResult.redirect(ALL_USERS_MESSAGE_PAGE);
    }
}
