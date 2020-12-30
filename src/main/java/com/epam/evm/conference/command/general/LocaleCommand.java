package com.epam.evm.conference.command.general;

import com.epam.evm.conference.command.Command;
import com.epam.evm.conference.command.CommandResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LocaleCommand implements Command {

    private final static String LOCALE_KEY = "locale";
    private final static String MAIN_JSP = "/WEB-INF/pages/main-page.jsp";
    private final String locale;

    public LocaleCommand(String locale) {
        this.locale = locale;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        session.setAttribute(LOCALE_KEY, locale);
//        String referer = request.getHeader("referer");

//        try {
//            URI uri = new URI(referer);
//            String query = uri.getQuery();
//            //session.setAttribute("var", query);
//            return CommandResult.forward(String.format("/controller?%s",query));
//        } catch (URISyntaxException e) {
//            throw new ServiceException("uri syntax error from locale command", e);
//        }

        return CommandResult.forward(MAIN_JSP);
    }
}
