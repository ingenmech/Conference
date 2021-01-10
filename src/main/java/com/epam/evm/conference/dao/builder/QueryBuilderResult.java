package com.epam.evm.conference.dao.builder;

import java.util.Map;
import java.util.Objects;

public class QueryBuilderResult {

    private final String query;
    private final Map<Integer, Object> fields;


    public QueryBuilderResult(String query, Map<Integer, Object> fields) {
        this.query = query;
        this.fields = fields;
    }

    public String getQuery() {
        return query;
    }

    public Map<Integer, Object> getFields() {
        return fields;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        QueryBuilderResult that = (QueryBuilderResult) o;

        if (!Objects.equals(query, that.query)) {
            return false;
        }
        return Objects.equals(fields, that.fields);
    }

    @Override
    public int hashCode() {
        int result = query != null ? query.hashCode() : 0;
        result = 31 * result + (fields != null ? fields.hashCode() : 0);
        return result;
    }
}
