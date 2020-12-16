package com.epam.evm.conference.dao;

import com.epam.evm.conference.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {

    Optional<T> findBiId(Long id) throws DaoException;
    List<T> getAll() throws DaoException;
    Optional<Long> save(T item) throws DaoException;
    void removeById(Long id) throws DaoException;

}
