package com.epam.evm.conference.dao.mapper;

import com.epam.evm.conference.exception.DaoException;

import java.sql.ResultSet;

public interface RowMapper<T> {

    T map(ResultSet resultSet) throws DaoException;
}
