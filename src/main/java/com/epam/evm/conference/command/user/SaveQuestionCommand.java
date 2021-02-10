package com.epam.evm.conference.command.user;

import com.epam.evm.conference.command.Command;
import com.epam.evm.conference.command.CommandResult;
import com.epam.evm.conference.exception.FieldValidationException;
import com.epam.evm.conference.exception.ServiceException;
import com.epam.evm.conference.service.QuestionService;
import com.epam.evm.conference.utils.NumberUtils;
import com.epam.evm.conference.web.RequestContent;

public class SaveQuestionCommand implements Command {

    private final static String USER_QUESTIONS_PAGE = "/controller?command=userQuestionsPage";
    private final static String USER_ID = "userId";
    private final static String CONFERENCE_ID = "conferenceId";
    private final static String CONTENT = "content";

    private final QuestionService service;

    public SaveQuestionCommand(QuestionService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(RequestContent requestContent) throws ServiceException {

        Object userIdRow = requestContent.getSessionAttribute(USER_ID);
        String conferenceIdRow = requestContent.getParameter(CONFERENCE_ID);
        if (!NumberUtils.isValidDigit(userIdRow.toString())) {
            throw new FieldValidationException("Field user id does not match format");
        }
        if (!NumberUtils.isValidDigit(conferenceIdRow)) {
            throw new FieldValidationException("Field conference id does not match format");
        }
        Long conferenceId = Long.parseLong(conferenceIdRow);
        Long userId = (Long) userIdRow;
        String content = requestContent.getParameter(CONTENT);

        service.saveQuestion(userId, conferenceId, content);
        return CommandResult.redirect(USER_QUESTIONS_PAGE);
    }
}
