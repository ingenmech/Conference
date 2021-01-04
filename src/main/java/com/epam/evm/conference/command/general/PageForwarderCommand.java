package com.epam.evm.conference.command.general;

import com.epam.evm.conference.command.Command;
import com.epam.evm.conference.command.CommandResult;
import com.epam.evm.conference.web.RequestContent;

public class PageForwarderCommand implements Command {

    private final String page;

    public PageForwarderCommand(String page) {
        this.page = page;
    }

    @Override
    public CommandResult execute(RequestContent content) {
        return CommandResult.forward(page);
    }
}
