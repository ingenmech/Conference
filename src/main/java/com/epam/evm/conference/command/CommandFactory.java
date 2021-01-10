package com.epam.evm.conference.command;

import com.epam.evm.conference.command.admin.*;
import com.epam.evm.conference.command.general.*;
import com.epam.evm.conference.command.user.*;
import com.epam.evm.conference.dao.helper.DaoHelperFactory;
import com.epam.evm.conference.model.RequestStatus;
import com.epam.evm.conference.service.*;
import com.epam.evm.conference.validator.DateUtils;
import com.epam.evm.conference.validator.NumberUtils;

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
    private final static String ADMIN_QUESTIONS_PAGE = "adminQuestionsPage";
    private final static String ACCEPT_REQUEST_PAGE_COMMAND = "/controller?command=adminGoToAcceptRequest";

    private final static String GO_TO_CREATE_CONFERENCE = "adminCreate";
    private final static String GO_TO_ACCEPT_REQUEST = "adminGoToAcceptRequest";

    private final static String USER_QUESTIONS_PAGE = "userQuestionsPage";
    private final static String USER_CREATE_QUESTION = "userCreateQuestion";
    private final static String USER_SEND_REQUEST = "userSendRequest";
    private final static String USER_REMOVE_REQUEST = "userRemoveRequest";
    private final static String SENT_REQUESTS_PAGE_COMMAND = "/controller?command=userSentRequests";
    private final static String GO_TO_CREATE_REQUEST = "userCreateRequest";
    private final static String GO_TO_SENT_REQUESTS = "userSentRequests";
    private final static String GO_TO_CREATE_QUESTION = "userCreateQuestionPage";

    private final static NumberUtils VALIDATOR = new NumberUtils();
    private final static DaoHelperFactory DAO_HELPER_FACTORY = new DaoHelperFactory();
    private final static DateUtils DATE_TIME_UTILS = new DateUtils();

    public static Command create(String command) {

        switch (command) {
            case GO_TO_SIGN_IN:
                return new PageForwarderCommand(SIGN_IN_PAGE);
            case GO_TO_MAIN:
                return new PageForwarderCommand(MAIN_PAGE);
            case GO_TO_CREATE_CONFERENCE:
                return new SaveConferencePageCommand();
            case GO_TO_CREATE_REQUEST:
                return new SaveRequestPageCommand(new ConferenceService(DAO_HELPER_FACTORY, VALIDATOR));
            case GO_TO_ACCEPT_REQUEST:
                return new AdminRequestsPageCommand(new RequestService(DAO_HELPER_FACTORY, VALIDATOR));
            case GO_TO_SENT_REQUESTS:
                return new UserRequestsPageCommand(new RequestService(DAO_HELPER_FACTORY, VALIDATOR));
            case GO_TO_CREATE_QUESTION:
                return new SaveQuestionPageCommand();
            case SHOW_CONFERENCE:
                return new ConferenceListCommand(new ConferenceService(DAO_HELPER_FACTORY, VALIDATOR));
            case USER_SEND_REQUEST:
                return new SaveRequestCommand(new RequestService(DAO_HELPER_FACTORY, VALIDATOR), VALIDATOR);
            case USER_REMOVE_REQUEST:
                return new RequestStatusCommand(RequestStatus.DEPRECATED, SENT_REQUESTS_PAGE_COMMAND, new RequestService(DAO_HELPER_FACTORY, VALIDATOR), VALIDATOR);
            case USER_QUESTIONS_PAGE:
                return new UserQuestionPageCommand(new QuestionService(DAO_HELPER_FACTORY, VALIDATOR));
            case USER_CREATE_QUESTION:
                return new SaveQuestionCommand(new QuestionService(DAO_HELPER_FACTORY, VALIDATOR), VALIDATOR);
            case ALL_USERS_ADD_MESSAGE:
                return new SaveMessageCommand(new MessageService(DAO_HELPER_FACTORY, VALIDATOR), VALIDATOR);
            case ALL_USERS_MESSAGE_PAGE:
                return new MessagePageCommand(new MessageService(DAO_HELPER_FACTORY, VALIDATOR));
            case ADMIN_SAVE_CONFERENCE:
                return new SaveConferenceCommand(new ConferenceService(DAO_HELPER_FACTORY, VALIDATOR), DATE_TIME_UTILS);
            case ADMIN_ACCEPT_REQUEST:
                return new RequestStatusCommand(RequestStatus.ACCEPTED, ACCEPT_REQUEST_PAGE_COMMAND, new RequestService(DAO_HELPER_FACTORY, VALIDATOR), VALIDATOR);
            case ADMIN_REJECT_REQUEST:
                return new RequestStatusCommand(RequestStatus.REJECTED, ACCEPT_REQUEST_PAGE_COMMAND, new RequestService(DAO_HELPER_FACTORY, VALIDATOR), VALIDATOR);
            case ADMIN_QUESTIONS_PAGE:
                return new AdminQuestionPageCommand(new QuestionService(DAO_HELPER_FACTORY, VALIDATOR));
            case LOGIN:
                return new LoginCommand(new UserService(DAO_HELPER_FACTORY, VALIDATOR));
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
