package com.epam.evm.conference.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LocaleFilter implements Filter {

    private final static String LOCALE = "locale";
    private final static String SESSION_LOCALE = "sessionLocale";

    @Override
    public void init(FilterConfig filterConfig){

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String locale = request.getParameter(SESSION_LOCALE);

        if(locale != null){
            HttpSession session = request.getSession();
            session.setAttribute(LOCALE, locale);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
