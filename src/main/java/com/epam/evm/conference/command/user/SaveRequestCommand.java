package com.epam.evm.conference.command.user;

import com.epam.evm.conference.command.Command;
import com.epam.evm.conference.command.CommandResult;
import com.epam.evm.conference.exception.FieldValidationException;
import com.epam.evm.conference.exception.ServiceException;
import com.epam.evm.conference.model.RequestStatus;
import com.epam.evm.conference.service.RequestService;
import com.epam.evm.conference.utils.NumberUtils;
import com.epam.evm.conference.web.RequestContent;

public class SaveRequestCommand implements Command {

    private final static String SECTION_ID = "section";
    private final static String TOPIC = "topic";
    private final static String USER_ID = "userId";
    private final static RequestStatus DEFAULT_STATUS = RequestStatus.CONSIDERING;
    private final static String GO_TO_SENT_REQUESTS = "/controller?command=userSentRequests";

    private final RequestService service;

    public SaveRequestCommand(RequestService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(RequestContent content) throws ServiceException {

        String rowSectionId = content.getParameter(SECTION_ID);
        Object rowUserId = content.getSessionAttribute(USER_ID);
        if(!NumberUtils.isValidDigit(rowUserId.toString())){
            throw new FieldValidationException("Field user id does not match format");
        }
        if(!NumberUtils.isValidDigit(rowSectionId)){
            throw new FieldValidationException("Field section id does not match format");
        }
        Long sectionId = Long.valueOf(rowSectionId);
        Long userId = (Long)rowUserId;
        String topicName = content.getParameter(TOPIC);

        service.saveRequest(sectionId, userId,  topicName, DEFAULT_STATUS);
        return CommandResult.redirect(GO_TO_SENT_REQUESTS);
    }
}
