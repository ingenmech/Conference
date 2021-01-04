package com.epam.evm.conference.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RoleFilter implements Filter {

    private final static String ROLE = "userRole";
    private final static String GUEST = "GUEST";
    private final static String MAIN_PAGE = "/WEB-INF/pages/main-page.jsp";

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute(ROLE);

        if(role == null){
            session.setAttribute(ROLE, GUEST);
           // ServletContext context = request.getServletContext();
//            RequestDispatcher dispatcher = context.getRequestDispatcher(MAIN_PAGE);
//            dispatcher.forward(request, response);
        }
            filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {
    }
}
