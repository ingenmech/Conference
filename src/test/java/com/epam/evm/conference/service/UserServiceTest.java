package com.epam.evm.conference.service;

import com.epam.evm.conference.dao.daoInterface.UserPersistentDao;
import com.epam.evm.conference.dao.helper.DaoHelper;
import com.epam.evm.conference.dao.helper.DaoHelperFactory;
import com.epam.evm.conference.exception.DaoException;
import com.epam.evm.conference.exception.ServiceException;
import com.epam.evm.conference.model.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    @Test
    public void testLoginShouldReturnUserIfLoginAndPasswordAreValid() throws ServiceException, DaoException {
        //given
        DaoHelperFactory factory = mock(DaoHelperFactory.class);
        DaoHelper helper = mock(DaoHelper.class);
        when(factory.create()).thenReturn(helper);
        UserPersistentDao dao = mock(UserPersistentDao.class);
        when(helper.createUserPersistentDao()).thenReturn(dao);
        when(dao.findUserByLoginAndPassword("admin", "pass"))
                .thenReturn(Optional.of(new User(null, "USER", "user", "pass")));
        UserService service = new UserService(factory);
        //when
        Optional<User> userOptional = service.login("admin", "pass");
        //then
        Assert.assertTrue(userOptional.isPresent());
    }

    @Test(expected = ServiceException.class)
    public void testLoginShouldThrowExceptionWhenDataInvalid() throws ServiceException, DaoException {
        //given
        DaoHelperFactory factory = mock(DaoHelperFactory.class);
        DaoHelper helper = mock(DaoHelper.class);
        when(factory.create()).thenReturn(helper);
        UserPersistentDao dao = mock(UserPersistentDao.class);
        when(helper.createUserPersistentDao()).thenReturn(dao);
        when(dao.findUserByLoginAndPassword("admin", "pass"))
                .thenReturn(Optional.empty());
        UserService service = new UserService(factory);
        //when
        service.login(null, "pass");
    }
}
