package com.epam.evm.conference.command.general;

import com.epam.evm.conference.command.Command;
import com.epam.evm.conference.command.CommandResult;
import com.epam.evm.conference.exception.ServiceException;
import com.epam.evm.conference.model.User;
import com.epam.evm.conference.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        Optional<User> guest = service.login(login, password);

        if (guest.isPresent()) {
            User user = guest.get();
            Long id = user.getId();
            String role = user.getRole();

            HttpSession session = request.getSession();
            session.setAttribute(ID, id);
            session.setAttribute(LOGIN, login);
            session.setAttribute(ROLE, role);
            return CommandResult.redirect(MAIN_PAGE);
        } else {
            request.setAttribute(ERROR, ERROR_MESSAGE);
            return CommandResult.forward(LOGIN_PAGE);
        }
    }
}
