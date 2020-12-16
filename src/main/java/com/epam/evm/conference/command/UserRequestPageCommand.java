package com.epam.evm.conference.command;

import com.epam.evm.conference.exception.ServiceException;
import com.epam.evm.conference.model.Topic;
import com.epam.evm.conference.service.GetService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class UserRequestPageCommand implements Command {

    private final static String GO_TO_SENT_REQUESTS = "/WEB-INF/pages/sent-requests-page.jsp";
    private final static String USER_ID = "userId";
    private final static String TOPIC_LIST = "userTopicList";

    private final GetService service;

    public UserRequestPageCommand(GetService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        HttpSession session = request.getSession();
        Long userId = (Long)session.getAttribute(USER_ID);

        List<Topic> topics = service.getAllTopicsByUserId(userId);

        String status = topics.get(0).getStatus();
        request.setAttribute(TOPIC_LIST, topics);

        return CommandResult.forward(GO_TO_SENT_REQUESTS);
    }
}
