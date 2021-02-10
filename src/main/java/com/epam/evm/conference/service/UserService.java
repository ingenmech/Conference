package com.epam.evm.conference.service;

import com.epam.evm.conference.dao.daoInterface.UserPersistentDao;
import com.epam.evm.conference.dao.helper.DaoHelper;
import com.epam.evm.conference.dao.helper.DaoHelperFactory;
import com.epam.evm.conference.exception.DaoException;
import com.epam.evm.conference.exception.FieldValidationException;
import com.epam.evm.conference.exception.ServiceException;
import com.epam.evm.conference.model.User;
import com.epam.evm.conference.utils.FieldUtils;

import java.util.Optional;

public class UserService {

    private final DaoHelperFactory daoHelperFactory;

    public UserService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    public Optional<User> login(String login, String password) throws ServiceException {

        if (!FieldUtils.isValidLength(login, FieldUtils.SHORT_SIZE)) {
            throw new FieldValidationException("Field login does not match format");
        }
        if (!FieldUtils.isValidLength(password, FieldUtils.SHORT_SIZE)) {
            throw new FieldValidationException("Field password does not match format");
        }

        try (DaoHelper daoHelper = daoHelperFactory.create()) {

            UserPersistentDao dao = daoHelper.createUserPersistentDao();
            return dao.findUserByLoginAndPassword(login, password);

        } catch (DaoException e) {
            throw new ServiceException("Login error", e);
        }
    }
}
