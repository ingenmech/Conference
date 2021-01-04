package com.epam.evm.conference.command;

import com.epam.evm.conference.exception.ServiceException;
import com.epam.evm.conference.web.RequestContent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
    CommandResult execute(RequestContent content) throws ServiceException;
}
