package com.epam.evm.conference.web.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ErrorFilter implements Filter {

    private final static String ERROR_JSP = "/WEB-INF/pages/error.jsp";
    private final static String ERROR_MESSAGE = "errorMessage";
    private final static Logger LOGGER = LogManager.getLogger(ErrorFilter.class);

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);

            String errorMessage = e.getMessage();
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            request.setAttribute(ERROR_MESSAGE, errorMessage);
            request.getRequestDispatcher(ERROR_JSP).forward(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
