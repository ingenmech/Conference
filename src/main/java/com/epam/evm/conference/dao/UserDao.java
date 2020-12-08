package com.epam.evm.conference.dao;

import com.epam.evm.conference.exception.DaoException;
import com.epam.evm.conference.model.User;

import java.util.Optional;

public interface UserDao extends Dao<User>{

    Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException;
}
