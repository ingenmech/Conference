package com.epam.evm.conference.dao;

import com.epam.evm.conference.dao.builder.QueryBuilder;
import com.epam.evm.conference.dao.builder.QueryBuilderResult;
import com.epam.evm.conference.dao.daoInterface.PersistentDao;
import com.epam.evm.conference.dao.extractor.FieldExtractor;
import com.epam.evm.conference.dao.mapper.RowMapper;
import com.epam.evm.conference.exception.DaoException;
import com.epam.evm.conference.model.Entity;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public abstract class AbstractPersistentDao<T extends Entity> extends AbstractDao<T> implements PersistentDao<T> {

    private final static String DELETE_BY_ID_QUERY = "DELETE FROM %s WHERE id = ?";
    private final FieldExtractor<T> extractor;
    private final static QueryBuilder QUERY_BUILDER = new QueryBuilder();

    protected AbstractPersistentDao(Connection connection, RowMapper<T> mapper, FieldExtractor<T> extractor, String table, String selectAllQuery) {
        super(connection, mapper, table, selectAllQuery);
        this.extractor = extractor;
    }


    @Override
    public void removeById(Long id) throws DaoException {
        String query = String.format(DELETE_BY_ID_QUERY, getTable());
        Map<Integer, Object> fields = new HashMap<>();
        fields.put(1, id);
        executeUpdate(query, fields);
    }

    @Override
    public Optional<Long> save(T entity) throws DaoException {

        Map<String, Object> fields = extractor.extract(entity);
        Long id = entity.getId();

        String query;
        Map<Integer, Object> queryFields;

        if (id == null) {
            QueryBuilderResult result = QUERY_BUILDER.buildSaveQuery(fields, getTable());
            query = result.getQuery();
            queryFields = result.getFields();
        } else {
            QueryBuilderResult result = QUERY_BUILDER.buildUpdateQuery(fields, getTable());
            query = result.getQuery();
            queryFields = result.getFields();
            Integer idKey = queryFields.size() + 1;
            queryFields.put(idKey, id);
        }
        return executeUpdate(query, queryFields);
    }
}
