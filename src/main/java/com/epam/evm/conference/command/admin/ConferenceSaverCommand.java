package com.epam.evm.conference.command.admin;

import com.epam.evm.conference.command.Command;
import com.epam.evm.conference.command.CommandResult;
import com.epam.evm.conference.exception.FieldValidationException;
import com.epam.evm.conference.exception.ServiceException;
import com.epam.evm.conference.service.ConferenceService;
import com.epam.evm.conference.validator.FieldValidator;
import com.epam.evm.conference.web.RequestContent;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ConferenceSaverCommand implements Command {

    private final static String CONFERENCE = "conference";
    private final static String SECTION = "section";
    private final static String DATE = "date";
    private final static String TIME = "time";
    private final static String CREATE_CONFERENCE_PAGE = "/controller?command=adminCreate";

    private final ConferenceService service;
    private final FieldValidator validator;

    public ConferenceSaverCommand(ConferenceService service, FieldValidator validator) {
        this.service = service;
        this.validator = validator;
    }

    @Override
    public CommandResult execute(RequestContent content) throws ServiceException {

        String date = content.getParameter(DATE);
        String time = content.getParameter(TIME);
//        if (!validator.isValid(date, "^\\d{4}-\\d{1,2}-\\d{1,2}$")
//                || !validator.isValid(time, "^\\d{1,2}:\\d{1,2}:\\d{1,2}$")) {
//            throw new FieldValidationException("Field does not match format");
//        }

        DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
        LocalDate localDate = LocalDate.parse(date, dateFormatter);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ISO_LOCAL_TIME;
        LocalTime localTime = LocalTime.parse(time, timeFormatter);
        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
        String name = content.getParameter(CONFERENCE);
        String[] sections = content.getParameterValues(SECTION);

        service.saveConferenceWithSection(name, localDateTime, sections);

        return CommandResult.redirect(CREATE_CONFERENCE_PAGE);
    }
}
