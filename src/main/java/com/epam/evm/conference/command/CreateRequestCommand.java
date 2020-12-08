package com.epam.evm.conference.command;

import com.epam.evm.conference.exception.DaoException;
import com.epam.evm.conference.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateRequestCommand implements Command{

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
