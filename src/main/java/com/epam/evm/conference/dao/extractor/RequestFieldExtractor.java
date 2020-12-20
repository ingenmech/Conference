package com.epam.evm.conference.dao.extractor;

import com.epam.evm.conference.model.Request;
import com.epam.evm.conference.model.RequestStatus;

import java.util.HashMap;
import java.util.Map;

public class RequestFieldExtractor implements FieldExtractor<Request> {

    private final static int USER_ID = 1;
    private final static int SECTION_ID = 2;
    private final static int TOPIC = 3;
    private final static int STATUS = 4;

    @Override
    public Map<Integer, Object> extract(Request entity) {

        Long userId = entity.getUserId();
        Long sectionId = entity.getSectionId();
        String topic = entity.getTopic();
        RequestStatus status = entity.getStatus();

        Map<Integer, Object> map = new HashMap<>();
        map.put(USER_ID, userId);
        map.put(SECTION_ID, sectionId);
        map.put(TOPIC, topic);
        map.put(STATUS, status.toString());

        return map;
    }
}
