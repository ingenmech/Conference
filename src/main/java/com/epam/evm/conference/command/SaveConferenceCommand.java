package com.epam.evm.conference.command;

import com.epam.evm.conference.exception.ServiceException;
import com.epam.evm.conference.model.Conference;
import com.epam.evm.conference.service.ConferenceService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class SaveConferenceCommand implements Command {

    private final static String CONFERENCE = "conference";
    private final static String SECTION = "section";
    private final static String DATE = "date";
    private final static String TIME = "time";
    private final static String MAIN_PAGE = "/controller?command=main";
    private final static String MESSAGE = "created";
    private final static String MESSAGE_KEY = "createMessage";

    private final ConferenceService service;

    public SaveConferenceCommand(ConferenceService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        String name = request.getParameter(CONFERENCE);
        String[] sections = request.getParameterValues(SECTION);
        List<String> sectionsList = Arrays.asList(sections);

        String date = request.getParameter(DATE);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
        LocalDate localDate = LocalDate.parse(date, dateFormatter);
        String time = request.getParameter(TIME);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ISO_LOCAL_TIME;
        LocalTime localTime = LocalTime.parse(time, timeFormatter);
        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);

        Conference conference = new Conference(null, name, localDateTime, sectionsList);
        service.save(conference);


        request.getSession().setAttribute("var", sections.length);
        request.getSession().setAttribute("varTwo", sections[1]);
        return CommandResult.redirect(MAIN_PAGE);
    }
}
