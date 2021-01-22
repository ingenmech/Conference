package com.epam.evm.conference.command.admin;

import com.epam.evm.conference.command.Command;
import com.epam.evm.conference.exception.ServiceException;
import com.epam.evm.conference.command.AbstractPaginationCommandTest;
import com.epam.evm.conference.model.QuestionDto;
import com.epam.evm.conference.service.QuestionService;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;

public class AdminQuestionPageCommandTest extends AbstractPaginationCommandTest<QuestionDto> {

    private final static String ADMIN_QUESTION_LIST = "questionsList";
    private final static List<QuestionDto> QUESTIONS = Arrays.asList(
            new QuestionDto(null, null, null, null),
            new QuestionDto(null, null, null, null)
    );

    public AdminQuestionPageCommandTest() {
        super(QUESTIONS, ADMIN_QUESTION_LIST);
    }

    @Override
    protected Command createCommand() throws ServiceException {

        QuestionService service = Mockito.mock(QuestionService.class);
        Mockito.when(service.findAllQuestionWithUserLogin(anyInt(), anyInt())).thenReturn(QUESTIONS);
        Mockito.when(service.countRows()).thenReturn(Long.valueOf("11"));
        return new AdminQuestionPageCommand(service);
    }
}
