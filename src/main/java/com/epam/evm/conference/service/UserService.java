package com.epam.evm.conference.service;

import com.epam.evm.conference.dao.daoInterface.UserDao;
import com.epam.evm.conference.dao.helper.DaoHelper;
import com.epam.evm.conference.dao.helper.DaoHelperFactory;
import com.epam.evm.conference.exception.DaoException;
import com.epam.evm.conference.exception.FieldValidationException;
import com.epam.evm.conference.exception.ServiceException;
import com.epam.evm.conference.model.User;
import com.epam.evm.conference.validator.FieldValidator;

import java.util.Optional;

public class UserService {

    private final static String REGEX = "^.{1,45}$";

    private final DaoHelperFactory daoHelperFactory;
    private final FieldValidator validator;

    public UserService(DaoHelperFactory daoHelperFactory, FieldValidator validator) {
        this.daoHelperFactory = daoHelperFactory;
        this.validator = validator;
    }

    public Optional<User> login(String login, String password) throws ServiceException {

        if (!validator.isValid(login, REGEX) || !validator.isValid(password, REGEX)){
            throw new FieldValidationException("Field does not match format");
        }

        try (DaoHelper daoHelper = daoHelperFactory.create()) {

            UserDao dao = daoHelper.createUserDao();
            return dao.findUserByLoginAndPassword(login, password);

        } catch (DaoException e) {
            throw new ServiceException("Login error", e);
        }
    }
}
