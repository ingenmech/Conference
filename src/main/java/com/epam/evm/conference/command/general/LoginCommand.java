package com.epam.evm.conference.command.general;

import com.epam.evm.conference.command.Command;
import com.epam.evm.conference.command.CommandResult;
import com.epam.evm.conference.exception.ServiceException;
import com.epam.evm.conference.model.User;
import com.epam.evm.conference.service.UserService;
import com.epam.evm.conference.web.RequestContent;

import java.util.Optional;

public class LoginCommand implements Command {

    private final static String ID = "userId";
    private final static String LOGIN = "login";
    private final static String PASSWORD = "password";
    private final static String ROLE = "userRole";
    private final static String MAIN_PAGE = "/controller?command=main";
    private final static String LOGIN_PAGE = "/WEB-INF/pages/login-page.jsp";
    private final static String ERROR = "errorMessage";
    private final static String ERROR_MESSAGE = "invalidLogin";

    private final UserService service;

    public LoginCommand(UserService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(RequestContent content) throws ServiceException {

        String login = content.getParameter(LOGIN);
        String password = content.getParameter(PASSWORD);
        Optional<User> guest = service.login(login, password);

        if (guest.isPresent()) {
            User user = guest.get();
            Long id = user.getId();
            String role = user.getRole();

            content.setSessionAttribute(ID, id);
            content.setSessionAttribute(LOGIN, login);
            content.setSessionAttribute(ROLE, role);
            return CommandResult.redirect(MAIN_PAGE);
        } else {
            content.setSessionAttribute(ERROR, ERROR_MESSAGE);
            return CommandResult.forward(LOGIN_PAGE);
        }
    }
}
