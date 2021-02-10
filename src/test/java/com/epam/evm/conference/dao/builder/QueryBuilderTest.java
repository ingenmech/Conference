package com.epam.evm.conference.dao.builder;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class QueryBuilderTest {


    @Test
    public void testBuildSaveQueryShouldReturnResultWhenDataArgumentIsCorrect(){
        //given
        String query = "INSERT INTO section(conference_id, user_id, name) VALUES (?, ?, ?)";
        Map<Integer, Object> expectedFields = new HashMap<>();
        expectedFields.put(3, "Name");
        expectedFields.put(1, Long.valueOf("1"));
        expectedFields.put(2, Long.valueOf("3"));
        QueryBuilderResult expectedResult = new QueryBuilderResult(query, expectedFields);
        Map<String, Object> fields = new HashMap<>();
        fields.put("name", "Name");
        fields.put("conference_id", Long.valueOf("1"));
        fields.put("user_id", Long.valueOf("3"));
        QueryBuilder builder = new QueryBuilder();
        //when
        QueryBuilderResult actualResult = builder.buildSaveQuery(fields, "section");
        //then
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testBuildUpdateQueryShouldReturnResultWhenDataArgumentIsCorrect(){
        //given
        String query = "UPDATE section SET conference_id = ?, user_id = ?, name = ? WHERE id = ?";
        Map<Integer, Object> expectedFields = new HashMap<>();
        expectedFields.put(3, "Name");
        expectedFields.put(1, Long.valueOf("1"));
        expectedFields.put(2, Long.valueOf("3"));
        QueryBuilderResult expectedResult = new QueryBuilderResult(query, expectedFields);
        Map<String, Object> fields = new HashMap<>();
        fields.put("name", "Name");
        fields.put("conference_id", Long.valueOf("1"));
        fields.put("user_id", Long.valueOf("3"));
        QueryBuilder builder = new QueryBuilder();
        //when
        QueryBuilderResult actualResult = builder.buildUpdateQuery(fields, "section");
        //then
        Assert.assertEquals(expectedResult, actualResult);
    }
}
