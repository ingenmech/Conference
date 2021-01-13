package com.epam.evm.conference.web;

import com.epam.evm.conference.command.Command;
import com.epam.evm.conference.command.CommandFactory;
import com.epam.evm.conference.command.CommandResult;
import com.epam.evm.conference.connection.ConnectionPool;
import com.epam.evm.conference.exception.DaoException;
import com.epam.evm.conference.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Servlet extends HttpServlet {

    private final static Logger LOGGER = LogManager.getLogger(Servlet.class);
    private final static String COMMAND = "command";
    private final static String ERROR_MESSAGE = "userErrorMessage";
    private final static String ERROR_JSP = "/WEB-INF/pages/error.jsp";
    private final static RequestContentHelper CONTENT_HELPER = new RequestContentHelper();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        process(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) {

        try {
            String commandParameter = request.getParameter(COMMAND);
            Command command = CommandFactory.create(commandParameter);
            RequestContent requestContent = CONTENT_HELPER.create(request);
            CommandResult result = command.execute(requestContent);
            CONTENT_HELPER.initRequest(requestContent, request);

            boolean isRedirect = result.isRedirect();
            String page = result.getPage();

            if (!isRedirect) {
                dispatch(page, request, response);
            } else {
                String path = request.getContextPath();
                response.sendRedirect(path + page);
            }
        } catch (ServletException | IOException | ServiceException e) {
            LOGGER.error(e.getMessage(), e);
//            String errorMessage = e.getMessage();
//            request.setAttribute(ERROR_MESSAGE, errorMessage);
//            try {
//                dispatch(ERROR_JSP, request, response);
//            } catch (IOException | ServletException c) {
//                LOGGER.error(c.getMessage(), c);
//            }
        }
    }

    private void dispatch(String page, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }

    @Override
    public void destroy() {
        super.destroy();
        ConnectionPool pool = ConnectionPool.getInstance();
        try {
            pool.shutDown();
        } catch (DaoException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
