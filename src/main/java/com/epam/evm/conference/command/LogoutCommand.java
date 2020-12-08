package com.epam.evm.conference.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutCommand implements Command {

    private final static String MAIN_PAGE = "/controller?command=main";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {

        request.getSession().invalidate();
        return CommandResult.redirect(MAIN_PAGE);
    }
}
