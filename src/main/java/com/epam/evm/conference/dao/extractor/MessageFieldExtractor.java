package com.epam.evm.conference.dao.extractor;

import com.epam.evm.conference.model.Message;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class MessageFieldExtractor implements FieldExtractor<Message>{

    private final static String QUESTION_ID = "question_id";
    private final static String USER_ID = "user_id";
    private final static String CONTENT = "content";


    @Override
    public Map<String, Object> extract(Message entity) {

        Long questionId = entity.getQuestionId();
        Long userId = entity.getUserId();
        String content = entity.getContent();

        Map<String, Object> map = new HashMap<>();
        map.put(QUESTION_ID, questionId);
        map.put(USER_ID, userId);
        map.put(CONTENT, content);

        return map;
    }

}
