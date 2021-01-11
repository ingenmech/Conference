package com.epam.evm.conference.command.general;

import com.epam.evm.conference.command.Command;
import com.epam.evm.conference.command.CommandResult;
import com.epam.evm.conference.web.RequestContent;

public class LocaleCommand implements Command {

    private final static String LOCALE_KEY = "locale";
    private final static String COMMAND = "command=";
    private final static String COMMAND_PART = "/controller?command=%s";
    private final static String CONTROLLER_PART = "/controller?%s";
    private final String locale;

    public LocaleCommand(String locale) {
        this.locale = locale;
    }

    @Override
    public CommandResult execute(RequestContent content) {
        
        String command =  (String) content.getSessionAttribute("page");
        String page;
        if (command.contains(COMMAND)) {
            page = String.format(CONTROLLER_PART, command);
        } else {
            page = String.format(COMMAND_PART, command);
        }
        content.setSessionAttribute(LOCALE_KEY, locale);

        return CommandResult.forward(page);
    }
}
