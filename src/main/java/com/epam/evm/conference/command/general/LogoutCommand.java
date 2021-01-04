package com.epam.evm.conference.command.general;

import com.epam.evm.conference.command.Command;
import com.epam.evm.conference.command.CommandResult;
import com.epam.evm.conference.web.RequestContent;

public class LogoutCommand implements Command {

    private final static String MAIN_JSP = "/WEB-INF/pages/main-page.jsp";  // /WEB-INF/pages/main-page.jsp
    private final static String ROLE = "userRole";
    private final static String GUEST = "GUEST";

    @Override
    public CommandResult execute(RequestContent request) {

        request.setSessionInvalidate(true);

        return CommandResult.forward(MAIN_JSP);
    }
}
