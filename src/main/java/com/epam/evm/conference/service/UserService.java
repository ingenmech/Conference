package com.epam.evm.conference.service;

import com.epam.evm.conference.dao.daoInterface.UserDao;
import com.epam.evm.conference.dao.helper.DaoHelper;
import com.epam.evm.conference.dao.helper.DaoHelperFactory;
import com.epam.evm.conference.exception.DaoException;
import com.epam.evm.conference.exception.FieldValidationException;
import com.epam.evm.conference.exception.ServiceException;
import com.epam.evm.conference.model.User;
import com.epam.evm.conference.validator.FieldUtils;

import java.util.Optional;

public class UserService {

    private final DaoHelperFactory daoHelperFactory;
    private final FieldUtils validator;

    public UserService(DaoHelperFactory daoHelperFactory, FieldUtils validator) {
        this.daoHelperFactory = daoHelperFactory;
        this.validator = validator;
    }

    public Optional<User> login(String login, String password) throws ServiceException {

        if (!validator.isValidShortLength(login)) {
            throw new FieldValidationException("Field login does not match format");
        }
        if (!validator.isValidShortLength(password)) {
            throw new FieldValidationException("Field password does not match format");
        }

        try (DaoHelper daoHelper = daoHelperFactory.create()) {

            UserDao dao = daoHelper.createUserDao();
            return dao.findUserByLoginAndPassword(login, password);

        } catch (DaoException e) {
            throw new ServiceException("Login error", e);
        }
    }
}
