package com.epam.evm.conference.command.admin;

import com.epam.evm.conference.command.Command;
import com.epam.evm.conference.command.CommandResult;
import com.epam.evm.conference.exception.FieldValidationException;
import com.epam.evm.conference.exception.ServiceException;
import com.epam.evm.conference.model.RequestStatus;
import com.epam.evm.conference.service.RequestService;
import com.epam.evm.conference.utils.NumberUtils;
import com.epam.evm.conference.web.RequestContent;

public class RequestStatusCommand implements Command {

    private final static String REQUEST_ID = "requestId";
    private final static String USER_ID = "userId";
    private final static String SECTION_ID = "sectionId";
    private final static String TOPIC = "topic";
    private final static String CONTROLLER_PART = "/controller?%s";
    private final static String PAGE = "page";

    private final RequestStatus status;
    private final RequestService service;

    public RequestStatusCommand(RequestStatus status, RequestService service) {
        this.status = status;
        this.service = service;
    }

    @Override
    public CommandResult execute(RequestContent content) throws ServiceException {

        String idRow = content.getParameter(REQUEST_ID);
        String userIdRow = content.getParameter(USER_ID);
        String sectionIdRow = content.getParameter(SECTION_ID);
        if(!NumberUtils.isValidDigit(idRow)){
            throw new FieldValidationException("Field request id does not match format");
        }
        if(!NumberUtils.isValidDigit(userIdRow)){
            throw new FieldValidationException("Field user id does not match format");
        }
        if(!NumberUtils.isValidDigit(sectionIdRow)){
            throw new FieldValidationException("Field section id does not match format");
        }
        Long id = Long.valueOf(idRow);
        Long userId = Long.valueOf(userIdRow);
        Long sectionId = Long.valueOf(sectionIdRow);
        String topic = content.getParameter(TOPIC);
        service.updateRequestStatus(id, sectionId, userId,  topic, status);

        String command =  (String) content.getSessionAttribute(PAGE);
        String page = String.format(CONTROLLER_PART, command);
        return CommandResult.redirect(page);
    }
}
