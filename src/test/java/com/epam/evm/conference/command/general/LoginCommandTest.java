package com.epam.evm.conference.command.general;

import com.epam.evm.conference.command.Command;
import com.epam.evm.conference.command.CommandResult;
import com.epam.evm.conference.exception.ServiceException;
import com.epam.evm.conference.model.User;
import com.epam.evm.conference.service.UserService;
import com.epam.evm.conference.web.RequestContent;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;

public class LoginCommandTest {

    private static RequestContent crateRequestContent(){
        Map<String, String[]> requestParams = new HashMap<>();
        requestParams.put("login", new String[]{"user"});
        requestParams.put("password", new String[]{"pass"});
        return new RequestContent(requestParams, new HashMap<>());
    }

    @Test
    public void testExecuteShouldShowMainPageWhenAuthenticated() throws ServiceException {
        //given
        UserService service = Mockito.mock(UserService.class);
        User user = new User(null, null, "user", "pass");
        Mockito.when(service.login(anyString(), anyString())).thenReturn(Optional.of(user));
        String expectedPage = "/controller?command=getConferences&pageSize=6";
        Command command = new LoginCommand(service);
        RequestContent content = crateRequestContent();
        //when
        CommandResult result = command.execute(content);
        String actualPage = result.getPage();
        //then
        Assert.assertEquals(expectedPage, actualPage);
    }

    @Test
    public void testExecuteShouldShowMainPageWhenInvalidAuthenticated() throws ServiceException {
        //given
        UserService service = Mockito.mock(UserService.class);
        Mockito.when(service.login(anyString(), anyString())).thenReturn(Optional.empty());
        String expectedPage = "/WEB-INF/pages/login-page.jsp";
        Command command = new LoginCommand(service);
        RequestContent content = crateRequestContent();
        //when
        CommandResult result = command.execute(content);
        String actualPage = result.getPage();
        //then
        Assert.assertEquals(expectedPage, actualPage);
    }
}
