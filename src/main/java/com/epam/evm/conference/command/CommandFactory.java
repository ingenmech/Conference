package com.epam.evm.conference.command;

import com.epam.evm.conference.dao.helper.DaoHelperFactory;
import com.epam.evm.conference.service.GetService;
import com.epam.evm.conference.service.SaveService;
import com.epam.evm.conference.service.LoginService;

public class CommandFactory {

    private final static String SIGN_IN_JSP = "/WEB-INF/pages/login-page.jsp";
    private final static String MAIN_JSP = "/WEB-INF/pages/main-page.jsp";
    private final static String CRATE_CONFERENCE_JSP = "/WEB-INF/pages/create-conference-page.jsp";
    private final static String ACCEPT_REQUEST_JSP = "/WEB-INF/pages/accept-request-page.jsp";
    private final static String SENT_REQUESTS_JSP = "/WEB-INF/pages/sent-requests-page.jsp";

    private final static String LOCALE_ENG = "en";
    private final static String LOCALE_RU = "ru";
    private final static String LOCALE_BY = "by";

    private final static String GO_TO_SIGN_IN = "signIn";
    private final static String GO_TO_MAIN = "main";
    private final static String GO_TO_CREATE_CONFERENCE = "adminCreate";
    private final static String GO_TO_ACCEPT_REQUEST = "adminGoToAcceptRequest";
    private final static String GO_TO_CREATE_REQUEST = "userCreateRequest";
    private final static String GO_TO_SENT_REQUESTS = "userSentRequests";

    private final static String LOGIN = "login";
    private final static String LOGOUT = "logout";
    private final static String SHOW_CONFERENCE = "getConference";

    private final static String ADMIN_SAVE_CONFERENCE = "adminSaveConference";
    private final static String ADMIN_SET_STATUS_REQUEST = "adminSetStatusRequest";
    private final static String ADMIN_REMOVE_REQUEST = "adminRemoveRequest";
    private final static String ADMIN_REJECT_REQUEST = "adminRejectRequest";
    private final static String ADMIN_ACCEPT_REQUEST = "adminAcceptRequest";

    private final static String USER_SEND_REQUEST = "userSendRequest";
    private final static String USER_REMOVE_REQUEST = "userRemoveRequest";

    public static Command create(String command) {

        switch (command) {
            case GO_TO_SIGN_IN:
                return new ForwardPageCommand(SIGN_IN_JSP);
            case GO_TO_MAIN:
                return new ForwardPageCommand(MAIN_JSP);
            case GO_TO_CREATE_CONFERENCE:
                return new ForwardPageCommand(CRATE_CONFERENCE_JSP);
            case GO_TO_CREATE_REQUEST:
                return new CreateRequestCommand(new GetService(new DaoHelperFactory()));
            case GO_TO_ACCEPT_REQUEST:
                return new AdminTopicPageCommand(new GetService(new DaoHelperFactory()));
            case GO_TO_SENT_REQUESTS:
                return new ForwardPageCommand(SENT_REQUESTS_JSP);
            case LOGIN:
                return new LoginCommand(new LoginService(new DaoHelperFactory()));
            case LOGOUT:
                return new LogoutCommand();
            case SHOW_CONFERENCE:
                return new ListCommand();
            case ADMIN_SAVE_CONFERENCE:
                return new SaveConferenceCommand(new SaveService(new DaoHelperFactory()));
            case USER_SEND_REQUEST:
                return new SaveTopicCommand(new SaveService(new DaoHelperFactory()));
            case LOCALE_ENG:
                return new LocaleCommand(LOCALE_ENG);
            case LOCALE_RU:
                return new LocaleCommand(LOCALE_RU);
            case LOCALE_BY:
                return new LocaleCommand(LOCALE_BY);
            default:
                throw new IllegalArgumentException(String.format("Not exist (%s) command.", command));
        }
    }
}
