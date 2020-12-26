package com.epam.evm.conference.command.general;

import com.epam.evm.conference.command.Command;
import com.epam.evm.conference.command.CommandResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements Command {

    private final static String MAIN_JSP = "/WEB-INF/pages/main-page.jsp";
    private final static String ROLE = "userRole";
    private final static String GUEST = "GUEST";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {

        request.getSession().invalidate();
        HttpSession session = request.getSession();
        session.setAttribute(ROLE, GUEST);

        return CommandResult.forward(MAIN_JSP);
    }
}
