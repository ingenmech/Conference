package com.epam.evm.conference.command;

import com.epam.evm.conference.exception.ServiceException;
import com.epam.evm.conference.web.RequestContent;

/**
 * The Command interface represent execute method
 * for the functionality of the view command.
 */
public interface Command {
    CommandResult execute(RequestContent content) throws ServiceException;
}
