package com.epam.evm.conference.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//TODO
public class ListConferenceCommand implements Command{

    private final static String LIST_CONFERENCE_PAGE = "WEB-INF/pages/list-conf.jsp";
    private final static String LIST = "list";


    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        return CommandResult.forward(LIST_CONFERENCE_PAGE);
    }
}
