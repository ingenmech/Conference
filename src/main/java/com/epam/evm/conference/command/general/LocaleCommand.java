package com.epam.evm.conference.command.general;

import com.epam.evm.conference.command.Command;
import com.epam.evm.conference.command.CommandResult;
import com.epam.evm.conference.web.RequestContent;

public class LocaleCommand implements Command {

    private final static String LOCALE_KEY = "locale";
    private final static String MAIN_JSP = "/WEB-INF/pages/main-page.jsp";
    private final String locale;

    public LocaleCommand(String locale) {
        this.locale = locale;
    }

    @Override
    public CommandResult execute(RequestContent request) {

        request.setSessionAttribute(LOCALE_KEY, locale);

        return CommandResult.forward(MAIN_JSP);
    }
}
