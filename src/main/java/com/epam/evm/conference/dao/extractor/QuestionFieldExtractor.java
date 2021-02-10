package com.epam.evm.conference.dao.extractor;

import com.epam.evm.conference.model.Question;

import java.util.HashMap;
import java.util.Map;

public class QuestionFieldExtractor implements FieldExtractor<Question>{

    private final static String USER_ID = "user_id";
    private final static String CONFERENCE_ID = "conference_id";
    private final static String CONTENT = "content";

    @Override
    public Map<String, Object> extract(Question entity) {

        Long userId = entity.getUserId();
        Long conferenceId = entity.getConferenceId();
        String content = entity.getContent();

        Map<String, Object> map = new HashMap<>();
        map.put(USER_ID, userId);
        map.put(CONFERENCE_ID, conferenceId);
        map.put(CONTENT, content);
        return map;
    }
}
