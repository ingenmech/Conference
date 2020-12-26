package com.epam.evm.conference.command.general;

import com.epam.evm.conference.command.Command;
import com.epam.evm.conference.command.CommandResult;
import com.epam.evm.conference.exception.ServiceException;
import com.epam.evm.conference.model.Message;
import com.epam.evm.conference.service.FindService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class MessagePageCommand implements Command {

    private final static String SEND_MASSAGES_PAGE = "/WEB-INF/pages/messages-page.jsp";
    private final static String QUESTION_ID = "questionId";
    private final static String MESSAGES_LIST = "messagesList";
    private final static String QUESTION_CONTENT = "questionContent";
    private final FindService service;

    public MessagePageCommand(FindService service) {
        this.service = service;
    }


    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        String questionIdRow = request.getParameter(QUESTION_ID);
        Long questionId = Long.valueOf(questionIdRow);

        String questionContent = request.getParameter(QUESTION_CONTENT);
        request.setAttribute(QUESTION_CONTENT, questionContent);
        request.setAttribute(QUESTION_ID, questionId);

        List<Message> messages = service.findMessagesByQuestionId(questionId);
        request.setAttribute(MESSAGES_LIST, messages);
        return CommandResult.forward(SEND_MASSAGES_PAGE);
    }
}
