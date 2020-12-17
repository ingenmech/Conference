package com.epam.evm.conference.dao.extractor;

import com.epam.evm.conference.model.Request;

import java.util.HashMap;
import java.util.Map;

public class RequestFieldExtractor implements FieldExtractor<Request> {

    private final static int ID = 1;
    private final static int USER_ID = 2;
    private final static int SECTION_ID = 3;
    private final static int TOPIC = 4;
    private final static int STATUS = 5;

    private final static int STATUS_UPDATE = 1;
    private final static int ID_UPDATE = 2;

    @Override
    public Map<Integer, Object> extractForSave(Request entity) {

        Long id = entity.getId();
        Long userId = entity.getUserId();
        Long sectionId = entity.getSectionId();
        String topic = entity.getTopic();
        String status = entity.getStatus();

        Map<Integer, Object> map = new HashMap<>();
        map.put(ID, id);
        map.put(USER_ID, userId);
        map.put(SECTION_ID, sectionId);
        map.put(TOPIC, topic);
        map.put(STATUS, status);

        return map;
    }

    @Override
    public Map<Integer, Object> extractForUpdate(Request entity) {

        String status = entity.getStatus();
        Long id = entity.getId();
        Map<Integer, Object> map = new HashMap<>();
        map.put(STATUS_UPDATE, status);
        map.put(ID_UPDATE, id);

        return map;
    }
}
