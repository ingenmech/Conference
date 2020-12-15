package com.epam.evm.conference.command;

import com.epam.evm.conference.exception.ServiceException;
import com.epam.evm.conference.model.Conference;
import com.epam.evm.conference.model.Section;
import com.epam.evm.conference.service.SaveService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SaveConferenceCommand implements Command {

    private final static String CONFERENCE = "conference";
    private final static String SECTION = "section";
    private final static String DATE = "date";
    private final static String TIME = "time";
    private final static String CREATE_CONFERENCE_PAGE = "/controller?command=adminCreate";
    private final static String MESSAGE = "conferenceCreated";
    private final static String MESSAGE_KEY = "message";

    private final SaveService service;

    public SaveConferenceCommand(SaveService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        String name = request.getParameter(CONFERENCE);

        String[] sectionsName = request.getParameterValues(SECTION);
        List<Section> sections = new ArrayList<>();
        for (int i = 0; i < sectionsName.length; i++) {
            sections.add(new Section(null, null, sectionsName[i]));
        }

        String date = request.getParameter(DATE);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
        LocalDate localDate = LocalDate.parse(date, dateFormatter);
        String time = request.getParameter(TIME);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ISO_LOCAL_TIME;
        LocalTime localTime = LocalTime.parse(time, timeFormatter);
        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);

        Conference conference = new Conference(null, name, localDateTime, sections);
        service.saveConferenceWithSection(conference);

        request.setAttribute(MESSAGE_KEY, MESSAGE);
        return CommandResult.redirect(CREATE_CONFERENCE_PAGE);
    }
}
