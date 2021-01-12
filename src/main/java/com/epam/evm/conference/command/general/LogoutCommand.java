package com.epam.evm.conference.command.general;

import com.epam.evm.conference.command.Command;
import com.epam.evm.conference.command.CommandResult;
import com.epam.evm.conference.web.RequestContent;

public class LogoutCommand implements Command {

    private final static String SIGN_IN_PAGE = "/WEB-INF/pages/login-page.jsp";

    @Override
    public CommandResult execute(RequestContent request) {
        request.setSessionInvalidate(true);
        return CommandResult.forward(SIGN_IN_PAGE);
    }
}
