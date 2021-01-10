package com.epam.evm.conference.dao.daoInterface;

import com.epam.evm.conference.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {

    Optional<T> findBiId(Long id) throws DaoException;
    List<T> findAll() throws DaoException;
    Optional<Long> save(T item) throws DaoException;
    void removeById(Long id) throws DaoException;
    List<T> findEntityForPagination(int limit, int offset) throws DaoException;

}
