package com.epam.evm.conference.dao.daoInterface;

import com.epam.evm.conference.exception.DaoException;
import com.epam.evm.conference.model.User;

import java.util.Optional;

public interface UserPersistentDao extends PersistentDao<User>{

    Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException;
}
