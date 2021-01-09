package com.epam.evm.conference.dao.builder;

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class QueryBuilder {

    public QueryBuilderResult buildSaveQuery(Map<String, Object> fields, String table) {

        String questionMark = "?";
        String prefix = String.format("INSERT INTO %s(", table);
        StringJoiner firstPart = new StringJoiner(", ", prefix, ")");
        StringJoiner secondPart = new StringJoiner(", ", " VALUES (", ")");
        Map<Integer, Object> queryMap = new HashMap<>();
        int i = 1;
        for (String field : fields.keySet()) {
            firstPart.add(field);
            secondPart.add(questionMark);

            Object value = fields.get(field);
            queryMap.put(i++, value);
        }
        String query = firstPart.toString() + secondPart;
        return new QueryBuilderResult(query, queryMap);
    }

    public QueryBuilderResult buildUpdateQuery(Map<String, Object> fields, String table) {

        String prefix = String.format("UPDATE %s SET ", table);
        StringJoiner queryJoiner = new StringJoiner(" = ?, ", prefix, " = ? WHERE id = ?");
        Map<Integer, Object> queryMap = new HashMap<>();
        int i = 1;
        for (String field : fields.keySet()) {
            queryJoiner.add(field);
            queryMap.put(i++, fields.get(field));
        }
        String query = queryJoiner.toString();

        return new QueryBuilderResult(query, queryMap);
    }

//    public String buildSaveQuery(Map<String, Object> map, String table) {
//        String questionMark = "?";
//        String prefix = String.format("INSERT INTO %s(", table);
//        StringJoiner keyJoiner = new StringJoiner(", ", prefix, ")");
//        StringJoiner valueJoiner = new StringJoiner(", ", " VALUES (", ")");
//        for (String key : map.keySet()) {
//            keyJoiner.add(key);
//            valueJoiner.add(questionMark);
//        }
//        return keyJoiner.toString() + valueJoiner;
//    }
//
//    public String buildUpdateQuery(Map<String, Object> map, String table) {
//
//        String prefix = String.format("UPDATE %s SET ", table);
//        StringJoiner keyJoiner = new StringJoiner(" = ?, ", prefix, "WHERE id = ?");
//        for (String key : map.keySet()) {
//            keyJoiner.add(key);
//        }
//        return keyJoiner.toString();
//    }
}
