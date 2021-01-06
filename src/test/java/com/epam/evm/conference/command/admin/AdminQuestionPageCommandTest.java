package com.epam.evm.conference.command.admin;

import com.epam.evm.conference.command.Command;
import com.epam.evm.conference.exception.ServiceException;
import com.epam.evm.conference.command.AbstractPaginationCommandTest;
import com.epam.evm.conference.model.Question;
import com.epam.evm.conference.service.QuestionService;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;

public class AdminQuestionPageCommandTest extends AbstractPaginationCommandTest {

    private final static String EXPECTED_PAGE = "/WEB-INF/pages/questions-page.jsp";

    public AdminQuestionPageCommandTest() {
        super(EXPECTED_PAGE);
    }

    @Override
    protected Command createCommand() throws ServiceException {
        List<Question> list = Arrays.asList(
                new Question(null, null, null, null),
                new Question(null, null, null, null));
        QuestionService service = Mockito.mock(QuestionService.class);
        Mockito.when(service.findAllQuestionWithUserLogin(anyInt(), anyInt())).thenReturn(list);
        return new AdminQuestionPageCommand(service);
    }
}
