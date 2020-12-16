package com.epam.evm.conference.command;

import com.epam.evm.conference.exception.ServiceException;
import com.epam.evm.conference.model.Topic;
import com.epam.evm.conference.service.UpdateService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StatusRequestCommand implements Command {

    private final static String ACCEPT_REQUEST_JSP = "/WEB-INF/pages/accept-request-page.jsp";
    private final static String TOPIC_ID = "topicId";

    private final String status;
    private final UpdateService service;

    public StatusRequestCommand(String status, UpdateService service) {
        this.status = status;
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        String idRow = request.getParameter(TOPIC_ID);
        Long id = Long.valueOf(idRow);
        Topic topic = new Topic(id, null, null, null, status);

        service.updateTopicStatus(topic);

        return CommandResult.forward(ACCEPT_REQUEST_JSP);
    }
}
