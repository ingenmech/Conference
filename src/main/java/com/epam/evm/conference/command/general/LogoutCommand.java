package com.epam.evm.conference.command.general;

import com.epam.evm.conference.command.Command;
import com.epam.evm.conference.command.CommandResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutCommand implements Command {

    private final static String MAIN_JSP = "/WEB-INF/pages/main-page.jsp";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {

        request.getSession().invalidate();
        return CommandResult.forward(MAIN_JSP);
    }
}
