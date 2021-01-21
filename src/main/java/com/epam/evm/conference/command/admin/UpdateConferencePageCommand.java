package com.epam.evm.conference.command.admin;

import com.epam.evm.conference.command.Command;
import com.epam.evm.conference.command.CommandResult;
import com.epam.evm.conference.exception.FieldValidationException;
import com.epam.evm.conference.exception.ServiceException;
import com.epam.evm.conference.model.Conference;
import com.epam.evm.conference.service.ConferenceService;
import com.epam.evm.conference.utils.NumberUtils;
import com.epam.evm.conference.web.RequestContent;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class UpdateConferencePageCommand implements Command {

    private final static String UPDATE_CONFERENCE_PAGE = "/WEB-INF/pages/update-conference-page.jsp";
    private final static String CONFERENCE = "conference";
    private final static String DATE = "conferenceDate";
    private final static String TIME = "conferenceTime";
    private final static String CONFERENCE_ID = "conferenceId";

    private final ConferenceService service;

    public UpdateConferencePageCommand(ConferenceService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(RequestContent content) throws ServiceException {

        String conferenceIdRow = content.getParameter(CONFERENCE_ID);
        if (!NumberUtils.isValidDigit(conferenceIdRow)) {
            throw new FieldValidationException("Field does not match format");
        }

        Long conferenceId = Long.parseLong(conferenceIdRow);
        Conference conference = service.findConferencesWithSectionsById(conferenceId);
        LocalDateTime dateTime = conference.getDate();
        LocalDate localDate = dateTime.toLocalDate();
        LocalTime localTime = dateTime.toLocalTime();
        String date = localDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
        String time = localTime.format(DateTimeFormatter.ISO_LOCAL_TIME);

        content.setAttribute(DATE, date);
        content.setAttribute(TIME, time);
        content.setAttribute(CONFERENCE, conference);
        return CommandResult.forward(UPDATE_CONFERENCE_PAGE);
    }

}
