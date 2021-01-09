package com.epam.evm.conference.command.admin;

import com.epam.evm.conference.command.Command;
import com.epam.evm.conference.command.CommandResult;
import com.epam.evm.conference.exception.FieldValidationException;
import com.epam.evm.conference.exception.ServiceException;
import com.epam.evm.conference.model.Request;
import com.epam.evm.conference.model.RequestStatus;
import com.epam.evm.conference.service.RequestService;
import com.epam.evm.conference.validator.FieldValidator;
import com.epam.evm.conference.web.RequestContent;

public class RequestStatusCommand implements Command {

    private final static String REQUEST_ID = "requestId";
    private final static String USER_ID = "userId";
    private final static String SECTION_ID = "sectionId";
    private final static String TOPIC = "topic";
    private final static String REGEXP = "^\\d*$";

    private final String page;
    private final RequestStatus status;
    private final RequestService service;
    private final FieldValidator validator;

    public RequestStatusCommand(RequestStatus status,String page, RequestService service, FieldValidator validator) {
        this.page = page;
        this.status = status;
        this.service = service;
        this.validator = validator;
    }

    @Override
    public CommandResult execute(RequestContent content) throws ServiceException {

        String idRow = content.getParameter(REQUEST_ID);
        String userIdRow = content.getParameter(USER_ID);
        String sectionIdRow = content.getParameter(SECTION_ID);

        if(!validator.isValid(idRow, REGEXP)
                || !validator.isValid(userIdRow, REGEXP)
                || !validator.isValid(sectionIdRow, REGEXP)){
            throw new FieldValidationException("Field does not match format");
        }

        Long id = Long.valueOf(idRow);
        Long userId = Long.valueOf(userIdRow);
        Long sectionId = Long.valueOf(sectionIdRow);
        String topic = content.getParameter(TOPIC);

        service.updateRequestStatus(id, sectionId, userId,  topic, status);
        return CommandResult.redirect(page);
    }
}
