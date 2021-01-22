package com.epam.evm.conference.dao.mapper;

import com.epam.evm.conference.exception.DaoException;

import java.sql.ResultSet;

/**
 * The {@code RowMapper} interface represents method signature
 * for map rows in entity from ResultSet.
 *
 * @param <T>
 * @version 1.0
 */
public interface RowMapper<T> {

    T map(ResultSet resultSet) throws DaoException;
}
