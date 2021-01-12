package com.epam.evm.conference.service;

import com.epam.evm.conference.dao.daoInterface.UserDao;
import com.epam.evm.conference.dao.helper.DaoHelper;
import com.epam.evm.conference.dao.helper.DaoHelperFactory;
import com.epam.evm.conference.exception.DaoException;
import com.epam.evm.conference.exception.ServiceException;
import com.epam.evm.conference.model.User;
import com.epam.evm.conference.validator.FieldUtils;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;

public class UserServiceTest {

    @Test
    public void testLoginShouldReturnUserIfLoginAndPasswordAreValid() throws ServiceException, DaoException {
        //given
        DaoHelperFactory factory = Mockito.mock(DaoHelperFactory.class);
        DaoHelper helper = Mockito.mock(DaoHelper.class);
        Mockito.when(factory.create()).thenReturn(helper);
        UserDao dao = Mockito.mock(UserDao.class);
        Mockito.when(helper.createUserDao()).thenReturn(dao);
        Mockito.when(dao.findUserByLoginAndPassword("admin", "pass")).thenReturn(Optional.of(new User(null, "USER", "user", "pass")));
        FieldUtils validator = Mockito.mock(FieldUtils.class);
        Mockito.when(validator.isValidShortLength(anyString())).thenReturn(true);
        UserService service = new UserService(factory, validator);
        //when
        Optional<User> userOptional = service.login("admin","pass");
        //then
        Assert.assertTrue(userOptional.isPresent());
    }

    @Test(expected = ServiceException.class)
    public void testLoginShouldThrowExceptionWhenDataInvalid() throws ServiceException, DaoException {
        //given
        DaoHelperFactory factory = Mockito.mock(DaoHelperFactory.class);
        DaoHelper helper = Mockito.mock(DaoHelper.class);
        Mockito.when(factory.create()).thenReturn(helper);
        UserDao dao = Mockito.mock(UserDao.class);
        Mockito.when(helper.createUserDao()).thenReturn(dao);
        Mockito.when(dao.findUserByLoginAndPassword("admin", "pass")).thenReturn(Optional.of(new User(null, "USER", "user", "pass")));
        FieldUtils validator = Mockito.mock(FieldUtils.class);
        Mockito.when(validator.isValidShortLength(anyString())).thenReturn(false);
        UserService service = new UserService(factory, validator);
        //when
        Optional<User> userOptional = service.login("admin","pass");
    }
}
