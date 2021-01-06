package com.epam.evm.conference.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SecurityFilter implements Filter {

    private final static String COMMAND = "command";
    private final static String ADMIN = "admin";
    private final static String USER = "user";
    private final static String ALL_USERS = "allUsers";
    private final static String ROLE = "userRole";
    private final static String GUEST = "GUEST";

    private final static String MAIN_PAGE = "/WEB-INF/pages/main-page.jsp";


    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String command = request.getParameter(COMMAND);
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute(ROLE);

        if (role == null) {
            session.setAttribute(ROLE, GUEST);
            role = GUEST;
        }

        if (command.startsWith(ADMIN) && !ADMIN.equalsIgnoreCase(role)
                || command.startsWith(USER) && !USER.equalsIgnoreCase(role)
                || command.startsWith(ALL_USERS) && GUEST.equalsIgnoreCase(role)) {

            ServletContext context = request.getServletContext();
            RequestDispatcher dispatcher = context.getRequestDispatcher(MAIN_PAGE);
            dispatcher.forward(request, response);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
