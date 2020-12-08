package com.epam.evm.conference.dao;

import com.epam.evm.conference.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {

    Optional<T> findBiId(Long id);
    List<T> getAll() throws DaoException;
    void save(T item) throws DaoException;
    void removeById(Long id);

}
