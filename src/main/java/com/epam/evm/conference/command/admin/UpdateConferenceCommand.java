package com.epam.evm.conference.command.admin;

import com.epam.evm.conference.command.Command;
import com.epam.evm.conference.command.CommandResult;
import com.epam.evm.conference.exception.FieldValidationException;
import com.epam.evm.conference.exception.ServiceException;
import com.epam.evm.conference.service.ConferenceService;
import com.epam.evm.conference.validator.DateUtils;
import com.epam.evm.conference.web.RequestContent;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UpdateConferenceCommand implements Command {

    private final static String CONFERENCE = "conference";
    private final static String SECTION = "section";
    private final static String DATE = "date";
    private final static String TIME = "time";
    private final static String CREATE_CONFERENCE_PAGE = "/controller?command=adminCreate";
    private final static String CONFERENCE_ID = "conferenceId";
    private final static String SECTIONS_ID = "sectionsId";
    private final static String SECTION_NAMES = "sectionNames";

    private final ConferenceService service;
    private final DateUtils utils;

    public UpdateConferenceCommand(ConferenceService service, DateUtils utils) {
        this.service = service;
        this.utils = utils;
    }

    @Override
    public CommandResult execute(RequestContent content) throws ServiceException {

        String conferenceIdRow = content.getParameter(CONFERENCE_ID);
        String[] sectionsIdRow = content.getParameterValues(SECTIONS_ID);
        String date = content.getParameter(DATE);
        String time = content.getParameter(TIME);
        if (!utils.isValidDate(date) || !utils.isValidTime(time)) {
            throw new FieldValidationException("Field does not match format");
        }

        Long conferenceId = Long.parseLong(conferenceIdRow);
        List<Long> sectionsId = fillList(Arrays.asList(sectionsIdRow));
        DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
        LocalDate localDate = LocalDate.parse(date, dateFormatter);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ISO_LOCAL_TIME;
        LocalTime localTime = LocalTime.parse(time, timeFormatter);
        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
        String name = content.getParameter(CONFERENCE);
        String[] sectionNames = content.getParameterValues(SECTION_NAMES);

        service.updateConferenceWithSection(conferenceId, name, localDateTime, sectionsId, sectionNames);
        return CommandResult.redirect(CREATE_CONFERENCE_PAGE);
    }

    private List<Long> fillList(List<String> idList) {
        List<Long> sectionsId = new ArrayList<>();
        for (String value : idList) {
            Long id = Long.parseLong(value);
            sectionsId.add(id);
        }
        return sectionsId;
    }
}
