package com.epam.evm.conference.dao;

import com.epam.evm.conference.dao.extractor.FieldExtractor;
import com.epam.evm.conference.dao.mapper.RowMapper;
import com.epam.evm.conference.exception.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class AbstractDao<T> implements Dao<T> {

    private final static String SELECT_BY_ID_QUERY = "SELECT * FROM %s WHERE id = ?";
    private final static String SELECT_ALL_QUERY = "SELECT * FROM %s";
    private final static String DELETE_BY_ID_QUERY = "DELETE * FROM %s WHERE id = ?";

    private final RowMapper<T> mapper;
    private final FieldExtractor<T> extractor;
    private final String table;
    private final String saveQuery;

    private Connection connection;

    protected AbstractDao(Connection connection, RowMapper<T> mapper, FieldExtractor<T> extractor,
                          String table, String saveQuery) {
        this.connection = connection;
        this.mapper = mapper;
        this.extractor = extractor;
        this.table = table;
        this.saveQuery = saveQuery;
    }

    @Override
    public void removeById(Long id) throws DaoException {

        String query = String.format(DELETE_BY_ID_QUERY, table);
        executeForSingleResult(query, id);
    }

    @Override
    public Optional<T> findBiId(Long id) throws DaoException {

        String query = String.format(SELECT_BY_ID_QUERY, table);
        return executeForSingleResult(query, id);
    }

    @Override
    public List<T> getAll() throws DaoException {

        String query = String.format(SELECT_ALL_QUERY, table);
        return executeQuery(query);
    }

    @Override
    public Long save(T entity) throws DaoException {

        Map<Integer, Object> fields = extractor.extract(entity);

        return executeUpdate(saveQuery, fields);
    }

    protected Long executeUpdate(String query, Map<Integer, Object> fields) throws DaoException {

        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            for (Integer key : fields.keySet()) {
                statement.setObject(key, fields.get(key));
            }
            statement.executeUpdate();

            ResultSet result = statement.getGeneratedKeys();
            if (result.next()) {
                return result.getLong(1);
            } else {
                throw new DaoException("No any auto generated keys");
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
