package com.epam.evm.conference.dao.extractor;

import com.epam.evm.conference.model.Request;
import com.epam.evm.conference.model.RequestStatus;

import java.util.HashMap;
import java.util.Map;

public class RequestFieldExtractor implements FieldExtractor<Request> {

    private final static String USER_ID = "user_id";
    private final static String SECTION_ID = "section_id";
    private final static String TOPIC = "topic";
    private final static String STATUS = "status";

    @Override
    public Map<String, Object> extract(Request entity) {

        Long userId = entity.getUserId();
        Long sectionId = entity.getSectionId();
        String topic = entity.getTopic();
        RequestStatus status = entity.getStatus();

        Map<String, Object> map = new HashMap<>();
        map.put(USER_ID, userId);
        map.put(SECTION_ID, sectionId);
        map.put(TOPIC, topic);
        map.put(STATUS, status.toString());

        return map;
    }
}
