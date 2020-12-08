package com.epam.evm.conference.command;

import com.epam.evm.conference.exception.DaoException;
import com.epam.evm.conference.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
    CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException;
}
