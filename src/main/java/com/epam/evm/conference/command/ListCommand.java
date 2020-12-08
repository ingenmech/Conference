package com.epam.evm.conference.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListCommand implements Command{

    private final static String LIST_CONFERENCE_PAGE = "WEB-INF/pages/list-conf.jsp";
    private final static String LIST = "list";

//    private final static Conference conference = new Conference("GENERAL_TOPIC",
//            Arrays.asList(new Section("Topic1"), new Section("Topic2"), new Section("topic3")));


    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        //request.setAttribute(LIST, conference.getSections());
        return CommandResult.forward(LIST_CONFERENCE_PAGE);
    }
}
