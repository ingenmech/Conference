package com.epam.evm.conference.dao.daoInterface;

import com.epam.evm.conference.exception.DaoException;

import java.util.List;
import java.util.Optional;

/**
 * The {@code Dao} interface contains signature find methods
 * for working with the database
 * * @param <T>
 *
 * @version 1.0
 */
public interface Dao<T> {

    Optional<T> findById(Long id) throws DaoException;

    List<T> findAll() throws DaoException;

    Long countRows() throws DaoException;

    List<T> findEntityForPagination(int limit, int offset) throws DaoException;
}
