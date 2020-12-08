package com.epam.evm.conference.dao;

import com.epam.evm.conference.dao.extractor.FieldExtractor;
import com.epam.evm.conference.dao.mapper.RowMapper;
import com.epam.evm.conference.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class AbstractDao<T> implements Dao<T> {

    private Connection connection;
    private final RowMapper<T> mapper;
    private final FieldExtractor<T> extractor;
    private final String table;

    protected AbstractDao(Connection connection, RowMapper<T> mapper, FieldExtractor<T> extractor, String table) {
        this.connection = connection;
        this.mapper = mapper;
        this.extractor = extractor;
        this.table = table;
    }

    @Override
    public void removeById(Long id) {
    }

    @Override
    public Optional<T> findBiId(Long id) {
        return Optional.empty();
    }

    @Override
    public List<T> getAll() throws DaoException {
        return executeQuery("SELECT * FROM " + table);
    }

    @Override
    public void save(T entity) throws DaoException {

        Map<Integer, Object> fields = extractor.extract(entity);

//        try (PreparedStatement statement = createPreparedStatement(query, fields)) {
//            statement.executeUpdate();
//        } catch (SQLException e) {
//        }
    }

    protected void createPreparedStatement(String query, Map<Integer, Object> fields) throws DaoException {

        try ( PreparedStatement statement = connection.prepareStatement(query)) {
            for (Integer key : fields.keySet()) {
                statement.setObject(key, fields.get(key));
            }
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Prepared statement query error ", e);
        }
    }

    protected void createStatementForSingleQuery(String query) throws DaoException {

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new DaoException("Single statement query error", e);
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

    //    protected void save(String query, Map<Integer, Object> fields) throws DaoException {
//
//        try (PreparedStatement statement = createPreparedStatement(query, fields)) {
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            throw new DaoException("Error single query statement", e);
//        }
//    }

    //    private PreparedStatement createPreparedStatement(String query, Map<Integer, Object> fields) throws SQLException {
//
//        PreparedStatement statement = connection.prepareStatement(query);
//            for (Integer key : fields.keySet()) {
//                statement.setObject(key, fields.get(key));
//            }
//        return statement;
//    }
}
