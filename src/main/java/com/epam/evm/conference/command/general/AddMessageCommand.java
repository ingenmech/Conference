package com.epam.evm.conference.command.general;

import com.epam.evm.conference.command.Command;
import com.epam.evm.conference.command.CommandResult;
import com.epam.evm.conference.exception.ServiceException;
import com.epam.evm.conference.model.Message;
import com.epam.evm.conference.service.SaveService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

public class AddMessageCommand implements Command {

    private final static String USER_ID = "userId";
    private final static String QUESTION_ID = "questionId";
    private final static String CONTENT = "content";

    private final static String ALL_USERS_MESSAGE_PAGE = "/controller?command=allUsersMessagePage";
    private final SaveService service;

    public AddMessageCommand(SaveService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute(USER_ID);

        String questionIdRow = request.getParameter(QUESTION_ID);
        Long questionId = Long.valueOf(questionIdRow);

        String content = request.getParameter(CONTENT);
        LocalDateTime dateTime = LocalDateTime.now();

        Message message = new Message(null, questionId, userId, dateTime,content);
        service.saveMessage(message);

        return CommandResult.forward(ALL_USERS_MESSAGE_PAGE);
    }
}
