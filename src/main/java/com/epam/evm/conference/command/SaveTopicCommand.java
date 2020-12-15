package com.epam.evm.conference.command;

import com.epam.evm.conference.exception.ServiceException;
import com.epam.evm.conference.model.Topic;
import com.epam.evm.conference.service.SaveService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SaveTopicCommand implements Command {

    private final static String SECTION_ID = "section";
    private final static String TOPIC = "topic";
    private final static String USER_ID = "userId";
    private final static String DEFAULT_STATUS = "considered";
    private final static String GO_TO_SENT_REQUESTS = "/controller?command=userSentRequests";

    private final SaveService service;

    public SaveTopicCommand(SaveService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        String rowSectionId = request.getParameter(SECTION_ID);
        Long sectionId = Long.valueOf(rowSectionId);
        HttpSession session = request.getSession();
        Long userId = (Long)session.getAttribute(USER_ID);
        String topicName = request.getParameter(TOPIC);

        Topic topic = new Topic(null, sectionId, userId,  topicName, DEFAULT_STATUS);
        service.saveTopic(topic);

        return CommandResult.redirect(GO_TO_SENT_REQUESTS);
    }
}
