package com.epam.evm.conference.command.admin;

import com.epam.evm.conference.command.Command;
import com.epam.evm.conference.command.CommandResult;
import com.epam.evm.conference.exception.FieldValidationException;
import com.epam.evm.conference.exception.ServiceException;
import com.epam.evm.conference.service.ConferenceService;
import com.epam.evm.conference.utils.DateUtils;
import com.epam.evm.conference.web.RequestContent;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class SaveConferenceCommand implements Command {

    private final static String CONFERENCE = "conference";
    private final static String SECTION = "section";
    private final static String DATE = "date";
    private final static String TIME = "time";
    private final static String CREATE_CONFERENCE_PAGE = "/controller?command=getConferences&pageSize=6";

    private final ConferenceService service;

    public SaveConferenceCommand(ConferenceService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(RequestContent content) throws ServiceException {

        String date = content.getParameter(DATE);
        String time = content.getParameter(TIME);
        if (!DateUtils.isValidDate(date)) {
            throw new FieldValidationException("Field date does not match format");
        }
        if (!DateUtils.isValidTime(time)) {
            throw new FieldValidationException("Field time does not match format");
        }
        DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
        LocalDate localDate = LocalDate.parse(date, dateFormatter);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ISO_LOCAL_TIME;
        LocalTime localTime = LocalTime.parse(time, timeFormatter);
        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
        String name = content.getParameter(CONFERENCE);
        String[] sections = content.getParameterValues(SECTION);

        try {
            service.saveConferenceWithSection(name, localDateTime, sections);
        } catch (ServiceException e) {
            content.setAttribute("warningMessage", "failSave");
            return CommandResult.forward("/WEB-INF/pages/create-conference-page.jsp");
        }
        return CommandResult.redirect(CREATE_CONFERENCE_PAGE);
    }
}
