package com.epam.evm.conference.dao;

import com.epam.evm.conference.dao.daoInterface.Dao;
import com.epam.evm.conference.dao.mapper.RowMapper;
import com.epam.evm.conference.exception.DaoException;

import java.sql.*;
import java.util.*;

public abstract class AbstractDao<T> implements Dao<T> {

    private final static String SELECT_BY_ID_QUERY = "SELECT * FROM %s WHERE id = ?";
    private final static String SELECT_ROWS_NUMBER = "SELECT COUNT(*) AS rows_number FROM %s";
    private final static String ROWS_NUMBER = "rows_number";

    private final RowMapper<T> mapper;
    private final String table;
    private final String selectAllQuery;

    private final Connection connection;

    protected AbstractDao(Connection connection, RowMapper<T> mapper, String table, String selectAllQuery) {
        this.connection = connection;
        this.mapper = mapper;
        this.table = table;
        this.selectAllQuery = selectAllQuery;
    }

    @Override
    public Optional<T> findById(Long id) throws DaoException {
        String query = String.format(SELECT_BY_ID_QUERY, table);
        return executeForSingleResult(query, id);
    }

    @Override
    public List<T> findAll() throws DaoException {
        return executeQuery(selectAllQuery);
    }

    @Override
    public Long countRows() throws DaoException {
        String query = String.format(SELECT_ROWS_NUMBER, table);
        return executeQueryCounter(query);
    }

    @Override
    public List<T> findEntityForPagination(int limit, int offset) throws DaoException {
        String query = selectAllQuery.concat(" LIMIT ? OFFSET ?");
        return executeQuery(query, limit, offset);
    }

    protected String getTable(){
        return table;
    }

    protected Long executeQueryCounter(String query, Object... params) throws DaoException {

        try (PreparedStatement statement = createStatement(query, params)){
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getLong(ROWS_NUMBER);
            } else {
                throw new DaoException("Not exist sql table for query");
            }
        } catch (SQLException e) {
            throw new DaoException("Statement query error ", e);
        }
    }

    protected Optional<Long> executeUpdate(String query, Map<Integer, Object> fields) throws DaoException {

        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            for (Integer key : fields.keySet()) {
                statement.setObject(key, fields.get(key));
            }
            statement.executeUpdate();

            ResultSet result = statement.getGeneratedKeys();
            if (result.next()) {
                Long id = result.getLong(1);
                return Optional.of(id);
            } else {
                return Optional.empty();
            }

        } catch (SQLException e) {
            throw new DaoException("Prepared statement query error ", e);
        }
    }

    protected List<T> executeQuery(String query, Object... params) throws DaoException {

        try (PreparedStatement statement = createStatement(query, params)) {
            ResultSet resultSet = statement.executeQuery();
            List<T> entities = new ArrayList<>();
            while (resultSet.next()) {
                T entity = mapper.map(resultSet);
                entities.add(entity);
            }
            return entities;

        } catch (SQLException e) {
            throw new DaoException("ExecuteQuery error", e);
        }
    }

    private PreparedStatement createStatement(String query, Object... params) throws SQLException {

        PreparedStatement statement = connection.prepareStatement(query);
        for (int i = 1; i <= params.length; i++) {
            statement.setObject(i, params[i - 1]);
        }
        return statement;
    }

    protected Optional<T> executeForSingleResult(String query, Object... params) throws DaoException {

        List<T> items = executeQuery(query, params);
        if (items.size() == 1) {
            return Optional.of(items.get(0));
        } else if (items.size() > 1) {
            throw new IllegalArgumentException("More then one record found");
        } else {
            return Optional.empty();
        }
    }
}
