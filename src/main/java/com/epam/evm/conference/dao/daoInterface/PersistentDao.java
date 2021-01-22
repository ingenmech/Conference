package com.epam.evm.conference.dao.daoInterface;

import com.epam.evm.conference.exception.DaoException;

import java.util.Optional;

/**
 * The PersistentDao interface extends Dao interface, contains
 * CRUD operations for working with the database.
 *
 * @param <T>
 * @version 1.0
 */
public interface PersistentDao<T> extends Dao<T> {
    Optional<Long> save(T item) throws DaoException;

    void removeById(Long id) throws DaoException;
}
