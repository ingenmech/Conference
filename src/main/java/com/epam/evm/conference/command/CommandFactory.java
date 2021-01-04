package com.epam.evm.conference.command;

import com.epam.evm.conference.command.admin.*;
import com.epam.evm.conference.command.general.*;
import com.epam.evm.conference.command.user.*;
import com.epam.evm.conference.dao.helper.DaoHelperFactory;
import com.epam.evm.conference.model.RequestStatus;
import com.epam.evm.conference.service.*;

public class CommandFactory {

    private final static String SIGN_IN_PAGE = "/WEB-INF/pages/login-page.jsp";
    private final static String MAIN_PAGE = "/WEB-INF/pages/main-page.jsp";

    private final static String LOCALE_ENG = "en";
    private final static String LOCALE_RU = "ru";
    private final static String LOCALE_BY = "by";

    private final static String GO_TO_SIGN_IN = "signIn";
    private final static String GO_TO_MAIN = "main";

    private final static String LOGIN = "login";
    private final static String LOGOUT = "logout";
    private final static String SHOW_CONFERENCE = "getConferences";
    private final static String ALL_USERS_ADD_MESSAGE = "allUsersAddMessage";
    private final static String ALL_USERS_MESSAGE_PAGE = "allUsersMessagePage";

    private final static String ADMIN_SAVE_CONFERENCE = "adminSaveConference";
    private final static String ADMIN_REJECT_REQUEST = "adminRejectRequest";
    private final static String ADMIN_ACCEPT_REQUEST = "adminAcceptRequest";
    private final static String ADMIN_REMOVE_REQUEST = "adminRemoveRequest";
    private final static String ADMIN_QUESTIONS_PAGE = "adminQuestionsPage";
    private final static String ACCEPT_REQUEST_PAGE_COMMAND = "/controller?command=adminGoToAcceptRequest";
    private final static String GO_TO_CREATE_CONFERENCE = "adminCreate";
    private final static String GO_TO_ACCEPT_REQUEST = "adminGoToAcceptRequest";
    private final static RequestStatus ACCEPTED = RequestStatus.ACCEPTED;
    private final static RequestStatus REJECTED = RequestStatus.REJECTED;

    private final static String USER_QUESTIONS_PAGE = "userQuestionsPage";
    private final static String USER_CREATE_QUESTION = "userCreateQuestion";
    private final static String USER_SEND_REQUEST = "userSendRequest";
    private final static String USER_REMOVE_REQUEST = "userRemoveRequest";
    private final static String SENT_REQUESTS_PAGE_COMMAND = "/controller?command=userSentRequests";
    private final static String GO_TO_CREATE_REQUEST = "userCreateRequest";
    private final static String GO_TO_SENT_REQUESTS = "userSentRequests";
    private final static String GO_TO_CREATE_QUESTION = "userCreateQuestionPage";


    public static Command create(String command) {

        switch (command) {
            case GO_TO_SIGN_IN:
                return new PageForwarderCommand(SIGN_IN_PAGE);
            case GO_TO_MAIN:
                return new PageForwarderCommand(MAIN_PAGE);
            case GO_TO_CREATE_CONFERENCE:
                return new ConferenceSaverPageCommand();
            case GO_TO_CREATE_REQUEST:
                return new RequestSaverPageCommand(new ConferenceService(new DaoHelperFactory()));
            case GO_TO_ACCEPT_REQUEST:
                return new AdminRequestsPageCommand(new RequestService(new DaoHelperFactory()));
            case GO_TO_SENT_REQUESTS:
                return new UserRequestsPageCommand(new RequestService(new DaoHelperFactory()));
            case GO_TO_CREATE_QUESTION:
                return new QuestionSaverPageCommand();
            case SHOW_CONFERENCE:
                return new ConferenceListCommand(new ConferenceService(new DaoHelperFactory()));
            case USER_SEND_REQUEST:
                return new RequestSaverCommand(new RequestService(new DaoHelperFactory()));
            case USER_REMOVE_REQUEST:
                return new RequestRemoverCommand(SENT_REQUESTS_PAGE_COMMAND, new RequestService(new DaoHelperFactory()));
            case USER_QUESTIONS_PAGE:
                return new UserQuestionPageCommand(new QuestionService(new DaoHelperFactory()));
            case USER_CREATE_QUESTION:
                return new QuestionSaverCommand(new QuestionService(new DaoHelperFactory()));
            case ALL_USERS_ADD_MESSAGE:
                return new MessageSaverCommand(new MessageService(new DaoHelperFactory()));
            case ALL_USERS_MESSAGE_PAGE:
                return new MessagePageCommand(new MessageService(new DaoHelperFactory()));
            case ADMIN_SAVE_CONFERENCE:
                return new ConferenceSaverCommand(new ConferenceService(new DaoHelperFactory()));
            case ADMIN_ACCEPT_REQUEST:
                return new RequestStatusCommand(ACCEPTED, new RequestService(new DaoHelperFactory()));
            case ADMIN_REJECT_REQUEST:
                return new RequestStatusCommand(REJECTED, new RequestService(new DaoHelperFactory()));
            case ADMIN_REMOVE_REQUEST:
                return new RequestRemoverCommand(ACCEPT_REQUEST_PAGE_COMMAND, new RequestService(new DaoHelperFactory()));
            case ADMIN_QUESTIONS_PAGE:
                return new AdminQuestionPageCommand(new QuestionService(new DaoHelperFactory()));
            case LOGIN:
                return new LoginCommand(new UserService(new DaoHelperFactory()));
            case LOGOUT:
                return new LogoutCommand();
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
