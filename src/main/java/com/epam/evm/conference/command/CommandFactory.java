package com.epam.evm.conference.command;

import com.epam.evm.conference.command.admin.*;
import com.epam.evm.conference.command.general.*;
import com.epam.evm.conference.command.user.*;
import com.epam.evm.conference.dao.helper.DaoHelperFactory;
import com.epam.evm.conference.model.RequestStatus;
import com.epam.evm.conference.service.*;
import com.epam.evm.conference.utils.NumberUtils;

public class CommandFactory {

    private final static String SIGN_IN_PAGE = "/WEB-INF/pages/login-page.jsp";
    private final static String MAIN_PAGE = "/WEB-INF/pages/main-page.jsp";
    private final static String CREATE_QUESTION_PAGE = "/WEB-INF/pages/create-question-page.jsp";
    private final static String CRATE_CONFERENCE_PAGE = "/WEB-INF/pages/create-conference-page.jsp";
    private final static String CREATE_REQUEST_PAGE = "/WEB-INF/pages/send-request-page.jsp";

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
    private final static String ADMIN_UPDATE_CONFERENCE_PAGE = "adminUpdateConferencePage";
    private final static String ADMIN_UPDATE_CONFERENCE = "adminUpdateConference";

    private final static String GO_TO_CREATE_CONFERENCE = "adminCreate";
    private final static String GO_TO_ACCEPT_REQUEST = "adminGoToAcceptRequest";

    private final static String USER_QUESTIONS_PAGE = "userQuestionsPage";
    private final static String USER_CREATE_QUESTION = "userCreateQuestion";
    private final static String USER_SEND_REQUEST = "userSendRequest";
    private final static String USER_REMOVE_REQUEST = "userRemoveRequest";
    private final static String GO_TO_CREATE_REQUEST = "userCreateRequest";
    private final static String USER_GO_TO_SENT_REQUESTS = "userSentRequests";
    private final static String USER_GO_TO_CREATE_QUESTION = "userCreateQuestionPage";

    private final static DaoHelperFactory DAO_HELPER_FACTORY = new DaoHelperFactory();

    public static Command create(String command) {

        switch (command) {
            case GO_TO_SIGN_IN:
                return new PageForwarderCommand(SIGN_IN_PAGE);
            case GO_TO_MAIN:
                return new PageForwarderCommand(MAIN_PAGE);
            case GO_TO_CREATE_CONFERENCE:
                return new PageForwarderCommand(CRATE_CONFERENCE_PAGE);
            case SHOW_CONFERENCE:
                return new ConferenceListCommand(new ConferenceService(DAO_HELPER_FACTORY));
            case GO_TO_CREATE_REQUEST:
                return new RequestQuestionPageCommand(CREATE_REQUEST_PAGE, new ConferenceService(DAO_HELPER_FACTORY));
            case GO_TO_ACCEPT_REQUEST:
                return new AdminRequestsPageCommand(new RequestService(DAO_HELPER_FACTORY));
            case ALL_USERS_ADD_MESSAGE:
                return new SaveMessageCommand(new MessageService(DAO_HELPER_FACTORY));
            case ALL_USERS_MESSAGE_PAGE:
                return new MessagePageCommand(new MessageService(DAO_HELPER_FACTORY));
            case USER_GO_TO_SENT_REQUESTS:
                return new UserRequestsPageCommand(new RequestService(DAO_HELPER_FACTORY));
            case USER_GO_TO_CREATE_QUESTION:
                return new RequestQuestionPageCommand(CREATE_QUESTION_PAGE, new ConferenceService(DAO_HELPER_FACTORY));
            case USER_SEND_REQUEST:
                return new SaveRequestCommand(new RequestService(DAO_HELPER_FACTORY));
            case USER_REMOVE_REQUEST:
                return new RequestStatusCommand(RequestStatus.DEPRECATED, new RequestService(DAO_HELPER_FACTORY));
            case USER_QUESTIONS_PAGE:
                return new UserQuestionPageCommand(new QuestionService(DAO_HELPER_FACTORY));
            case USER_CREATE_QUESTION:
                return new SaveQuestionCommand(new QuestionService(DAO_HELPER_FACTORY));
            case ADMIN_SAVE_CONFERENCE:
                return new SaveConferenceCommand(new ConferenceService(DAO_HELPER_FACTORY));
            case ADMIN_ACCEPT_REQUEST:
                return new RequestStatusCommand(RequestStatus.ACCEPTED,
                        new RequestService(DAO_HELPER_FACTORY));
            case ADMIN_REJECT_REQUEST:
                return new RequestStatusCommand(RequestStatus.REJECTED,
                        new RequestService(DAO_HELPER_FACTORY));
            case ADMIN_QUESTIONS_PAGE:
                return new AdminQuestionPageCommand(new QuestionService(DAO_HELPER_FACTORY));
            case ADMIN_UPDATE_CONFERENCE_PAGE:
                return new UpdateConferencePageCommand(new ConferenceService(DAO_HELPER_FACTORY));
            case ADMIN_UPDATE_CONFERENCE:
                return new UpdateConferenceCommand(new ConferenceService(DAO_HELPER_FACTORY));
            case LOGIN:
                return new LoginCommand(new UserService(DAO_HELPER_FACTORY));
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
