package com.epam.evm.conference.service;

import com.epam.evm.conference.dao.helper.DaoHelper;
import com.epam.evm.conference.dao.helper.DaoHelperCreator;
import com.epam.evm.conference.dao.UserDao;
import com.epam.evm.conference.exception.DaoException;
import com.epam.evm.conference.exception.ServiceException;
import com.epam.evm.conference.model.User;

import java.util.Optional;


public class LoginService {

    private DaoHelperCreator daoHelperFactory;

    public LoginService(DaoHelperCreator daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    public Optional<User> login(String login, String password) throws ServiceException {

        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            UserDao dao = daoHelper.createUserDao();
            return dao.findUserByLoginAndPassword(login, password);
        } catch (DaoException e) {
            throw new ServiceException("Login error", e);
        }
    }
}