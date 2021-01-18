package com.epam.evm.conference.command.admin;

import com.epam.evm.conference.command.Command;
import com.epam.evm.conference.command.CommandResult;
import com.epam.evm.conference.exception.FieldValidationException;
import com.epam.evm.conference.exception.ServiceException;
import com.epam.evm.conference.service.ConferenceService;
import com.epam.evm.conference.validator.DateUtils;
import com.epam.evm.conference.validator.NumberUtils;
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
    private final static String STATUS = "status";
    private final static String DATE = "date";
    private final static String TIME = "time";
    private final static String CONFERENCE_ID = "conferenceId";
    private final static String SECTION_ID = "sectionId";
    private final static String CONTROLLER_PART = "/controller?%s";
    private final static String PAGE = "returnEditPage";

    private final ConferenceService service;
    private final DateUtils dateUtils;
    private final NumberUtils numberUtils;

    public UpdateConferenceCommand(ConferenceService service, DateUtils dateUtils, NumberUtils numberUtils) {
        this.service = service;
        this.dateUtils = dateUtils;
        this.numberUtils = numberUtils;
    }

    @Override
    public CommandResult execute(RequestContent content) throws ServiceException {

        String conferenceIdRow = content.getParameter(CONFERENCE_ID);
        String[] sectionsIdRow = content.getParameterValues(SECTION_ID);
        String date = content.getParameter(DATE);
        String time = content.getParameter(TIME);

        if (!dateUtils.isValidDate(date)) {
            throw new FieldValidationException("Field date does not match format");
        }
        if (!dateUtils.isValidTime(time)) {
            throw new FieldValidationException("Field time does not match format");
        }
        if (!numberUtils.isValidDigit(conferenceIdRow)) {
            throw new FieldValidationException("Conference id does not digit");
        }
        if (!numberUtils.isValidDigit(sectionsIdRow)) {
            throw new FieldValidationException("Section id does not digit");
        }
        Long conferenceId = Long.parseLong(conferenceIdRow);
        List<Long> sectionsId = createIdList(Arrays.asList(sectionsIdRow));
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
        LocalTime localTime = LocalTime.parse(time, DateTimeFormatter.ISO_LOCAL_TIME);
        LocalDateTime dateTime = LocalDateTime.of(localDate, localTime);
        String name = content.getParameter(CONFERENCE);
        String[] sectionNames = content.getParameterValues(SECTION);
        String[] sectionStatuses = content.getParameterValues(STATUS);

        String command = (String) content.getSessionAttribute(PAGE);
        String page = String.format(CONTROLLER_PART, command);
        try {
            service.updateConferenceWithSection(conferenceId, name, dateTime, sectionsId, sectionNames, sectionStatuses);
        } catch (ServiceException e) {
            content.setAttribute("warningMessage", "failSave");
            return CommandResult.forward(page);
        }
        return CommandResult.redirect(page);
    }

    private List<Long> createIdList(List<String> idList) {
        List<Long> sectionsId = new ArrayList<>();
        for (String value : idList) {
            Long id = Long.parseLong(value);
            sectionsId.add(id);
        }
        return sectionsId;
    }
}
