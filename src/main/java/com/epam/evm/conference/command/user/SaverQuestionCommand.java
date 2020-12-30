package com.epam.evm.conference.command.user;

import com.epam.evm.conference.command.Command;
import com.epam.evm.conference.command.CommandResult;
import com.epam.evm.conference.exception.ServiceException;
import com.epam.evm.conference.model.Question;
import com.epam.evm.conference.service.SaveService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SaverQuestionCommand implements Command {

    private final static String USER_QUESTIONS_PAGE = "/controller?command=userQuestionsPage";
    private final static String USER_ID = "userId";
    private final static String CONTENT = "content";

    private final SaveService service;

    public SaverQuestionCommand(SaveService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute(USER_ID);

        String content = request.getParameter(CONTENT);

        Question question = new Question(null, userId, content);
        service.saveQuestion(question);

        return CommandResult.forward(USER_QUESTIONS_PAGE);
    }
}
