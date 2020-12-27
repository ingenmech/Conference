package com.epam.evm.conference.dao;

import com.epam.evm.conference.dao.daoInterface.Dao;
import com.epam.evm.conference.dao.extractor.FieldExtractor;
import com.epam.evm.conference.dao.mapper.RowMapper;
import com.epam.evm.conference.exception.DaoException;
import com.epam.evm.conference.model.DatabaseEntity;

import java.sql.*;
import java.util.*;

public abstract class AbstractDao<T extends DatabaseEntity> implements Dao<T> {

    private final static String SELECT_BY_ID_QUERY = "SELECT * FROM %s WHERE id = ?";
    private final static String DELETE_BY_ID_QUERY = "DELETE FROM %s WHERE id = ?";
    private final static String SELECT_BY_LIMIT = "SELECT * FROM %s limit ? offset ?";
    private final static String SELECT_ROWS_NUMBER = "SELECT COUNT(*) AS rowsNumber FROM %s";

    private final RowMapper<T> mapper;
    private final FieldExtractor<T> extractor;
    private final String table;
    private final String saveQuery;
    private final String updateQuery;
    private final String selectAllQuery;

    private final Connection connection;

    protected AbstractDao(Connection connection, RowMapper<T> mapper, FieldExtractor<T> extractor,
                          String table, String saveQuery, String updateQuery, String selectAllQuery) {
        this.connection = connection;
        this.mapper = mapper;
        this.extractor = extractor;
        this.table = table;
        this.saveQuery = saveQuery;
        this.updateQuery = updateQuery;
        this.selectAllQuery = selectAllQuery;
    }

    @Override
    public void removeById(Long id) throws DaoException {

        String query = String.format(DELETE_BY_ID_QUERY, table);
        Map<Integer, Object> fields = new HashMap<>();
        fields.put(1, id);
        executeUpdate(query, fields);
    }

    @Override
    public Optional<T> findBiId(Long id) throws DaoException {

        String query = String.format(SELECT_BY_ID_QUERY, table);
        return executeForSingleResult(query, id);
    }

    @Override
    public List<T> getAll() throws DaoException {

        return executeQuery(selectAllQuery);
    }

    public List<T> findEntityByLimit(int limit, int offset) throws DaoException {

        String query = String.format(SELECT_BY_LIMIT, table);
        return executeQuery(query, limit, offset);
    }

    public Long countRows() throws DaoException, SQLException {

        String query = String.format(SELECT_ROWS_NUMBER, table);
        PreparedStatement statement = createStatement(query);
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        return resultSet.getLong(0);
    }

    @Override
    public Optional<Long> save(T entity) throws DaoException {

        Map<Integer, Object> fields = extractor.extract(entity);
        Long id = entity.getId();
        String query;

        if (id == null) {
            query = saveQuery;
        } else {
            query = updateQuery;
            Integer updateId = fields.size() + 1;
            fields.put(updateId, id);
        }

        return executeUpdate(query, fields);
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


// TODO for paging
//    protected <E> List<E> executeQuery(String query, Object... params) throws DaoException {

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
