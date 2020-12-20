package com.epam.evm.conference.dao.extractor;

import com.epam.evm.conference.model.Message;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class MessageFieldExtractor implements FieldExtractor<Message>{

    private final static int QUESTION_ID = 1;
    private final static int USER_ID = 2;
    private final static int DATE_TIME = 3;
    private final static int CONTENT = 4;


    @Override
    public Map<Integer, Object> extract(Message entity) {

        Long questionId = entity.getQuestionId();
        Long userId = entity.getUserId();
        LocalDateTime dateTime = entity.getDateTime();
        String content = entity.getContent();

        Map<Integer, Object> map = new HashMap<>();
        map.put(QUESTION_ID, questionId);
        map.put(USER_ID, userId);
        map.put(DATE_TIME, dateTime);
        map.put(CONTENT, content);

        return map;
    }

}
