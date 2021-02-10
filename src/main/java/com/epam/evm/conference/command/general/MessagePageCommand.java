package com.epam.evm.conference.command.general;

import com.epam.evm.conference.command.Command;
import com.epam.evm.conference.command.CommandResult;
import com.epam.evm.conference.exception.ServiceException;
import com.epam.evm.conference.model.MessageDto;
import com.epam.evm.conference.service.MessageService;
import com.epam.evm.conference.web.RequestContent;

import java.util.List;

public class MessagePageCommand implements Command {

    private final static String SEND_MASSAGES_PAGE = "/WEB-INF/pages/messages-page.jsp";
    private final static String QUESTION_ID = "questionId";
    private final static String MESSAGES_LIST = "messagesList";
    private final static String QUESTION_CONTENT = "questionContent";
    private final MessageService service;

    public MessagePageCommand(MessageService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(RequestContent content) throws ServiceException {

        String questionIdRow = content.getParameter(QUESTION_ID);
        Long questionId = Long.parseLong(questionIdRow);

        String questionContent = content.getParameter(QUESTION_CONTENT);
        content.setAttribute(QUESTION_CONTENT, questionContent);
        content.setAttribute(QUESTION_ID, questionId);

        List<MessageDto> messages = service.findMessagesByQuestionId(questionId);
        content.setAttribute(MESSAGES_LIST, messages);
        return CommandResult.forward(SEND_MASSAGES_PAGE);
    }
}
