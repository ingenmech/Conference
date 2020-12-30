package com.epam.evm.conference.command.user;

import com.epam.evm.conference.command.Command;
import com.epam.evm.conference.command.CommandResult;
import com.epam.evm.conference.exception.ServiceException;
import com.epam.evm.conference.model.Request;
import com.epam.evm.conference.model.RequestStatus;
import com.epam.evm.conference.service.RequestService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SaverRequestCommand implements Command {

    private final static String SECTION_ID = "section";
    private final static String TOPIC = "topic";
    private final static String USER_ID = "userId";
    private final static RequestStatus DEFAULT_STATUS = RequestStatus.CONSIDERING;
    private final static String GO_TO_SENT_REQUESTS = "/controller?command=userSentRequests";

    private final RequestService service;

    public SaverRequestCommand(RequestService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        String rowSectionId = request.getParameter(SECTION_ID);
        Long sectionId = Long.valueOf(rowSectionId);
        HttpSession session = request.getSession();
        Long userId = (Long)session.getAttribute(USER_ID);
        String topicName = request.getParameter(TOPIC);

        Request topic = new Request(null, sectionId, userId,  topicName, DEFAULT_STATUS);
        service.saveRequest(topic);

        return CommandResult.redirect(GO_TO_SENT_REQUESTS);
    }
}
