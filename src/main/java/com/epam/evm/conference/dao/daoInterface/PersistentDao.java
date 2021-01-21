package com.epam.evm.conference.dao.daoInterface;

import com.epam.evm.conference.exception.DaoException;

import java.util.Optional;

public interface PersistentDao<T> extends Dao<T>{
    Optional<Long> save(T item) throws DaoException;
    void removeById(Long id) throws DaoException;
}
