package com.epam.evm.conference.dao.extractor;

import com.epam.evm.conference.model.Question;

import java.util.HashMap;
import java.util.Map;

public class QuestionFieldExtractor implements FieldExtractor<Question>{

    private final static int ID = 0;
    private final static int USER_ID = 1;
    private final static int CONTENT = 2;

    @Override
    public Map<Integer, Object> extractForSave(Question entity) {

        Long userId = entity.getUserId();
        String content = entity.getContent();

        Map<Integer, Object> map = new HashMap<>();
        map.put(USER_ID, userId);
        map.put(CONTENT, content);
        return map;
    }

    @Override
    public Map<Integer, Object> extractForUpdate(Question entity) {
        return null;
    }
}
