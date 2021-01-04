package com.epam.evm.conference.command.admin;

import com.epam.evm.conference.command.Command;
import com.epam.evm.conference.command.CommandResult;
import com.epam.evm.conference.exception.ServiceException;
import com.epam.evm.conference.model.Conference;
import com.epam.evm.conference.model.Section;
import com.epam.evm.conference.service.ConferenceService;
import com.epam.evm.conference.web.RequestContent;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ConferenceSaverCommand implements Command {

    private final static String CONFERENCE = "conference";
    private final static String SECTION = "section";
    private final static String DATE = "date";
    private final static String TIME = "time";
    private final static String CREATE_CONFERENCE_PAGE = "/controller?command=adminCreate";
    //private final static String MESSAGE = "conferenceCreated";
    //private final static String MESSAGE_KEY = "message";

    private final ConferenceService service;

    public ConferenceSaverCommand(ConferenceService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(RequestContent content) throws ServiceException {

        String name = content.getParameter(CONFERENCE);

        String[] sectionsName = content.getParameterValues(SECTION);
        List<Section> sections = new ArrayList<>();
        for (String value : sectionsName) {
            sections.add(new Section(null, null, value));
        }

        String date = content.getParameter(DATE);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
        LocalDate localDate = LocalDate.parse(date, dateFormatter);
        String time = content.getParameter(TIME);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ISO_LOCAL_TIME;
        LocalTime localTime = LocalTime.parse(time, timeFormatter);
        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);

        Conference conference = new Conference(null, name, localDateTime, sections);
        service.saveConferenceWithSection(conference);

        //content.setAttribute(MESSAGE_KEY, MESSAGE);
        return CommandResult.redirect(CREATE_CONFERENCE_PAGE);
    }
}
