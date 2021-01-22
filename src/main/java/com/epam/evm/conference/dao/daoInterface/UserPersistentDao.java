package com.epam.evm.conference.dao.daoInterface;

import com.epam.evm.conference.exception.DaoException;
import com.epam.evm.conference.model.User;

import java.util.Optional;

/**
 * The {@code UserPersistentDao} interface extends PersistentDao interface
 * and represents specific method signature for find user in database.
 *
 * @version 1.0
 */
public interface UserPersistentDao extends PersistentDao<User> {

    Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException;
}
