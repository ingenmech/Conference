package com.epam.evm.conference.dao.extractor;

import com.epam.evm.conference.model.Topic;

import java.util.HashMap;
import java.util.Map;

public class TopicFieldExtractor implements FieldExtractor<Topic> {

    private final static int ID = 1;
    private final static int USER_ID = 2;
    private final static int SECTION_ID = 3;
    private final static int NAME = 4;
    private final static int STATUS = 5;

    private final static int STATUS_UPDATE = 1;
    private final static int ID_UPDATE = 2;

    @Override
    public Map<Integer, Object> extractForSave(Topic entity) {

        Long id = entity.getId();
        Long userId = entity.getUserId();
        Long sectionId = entity.getSectionId();
        String name = entity.getName();
        String status = entity.getStatus();

        Map<Integer, Object> map = new HashMap<>();
        map.put(ID, id);
        map.put(USER_ID, userId);
        map.put(SECTION_ID, sectionId);
        map.put(NAME, name);
        map.put(STATUS, status);

        return map;
    }

    @Override
    public Map<Integer, Object> extractForUpdate(Topic entity) {

        String status = entity.getStatus();
        Long id = entity.getId();
        Map<Integer, Object> map = new HashMap<>();
        map.put(STATUS_UPDATE, status);
        map.put(ID_UPDATE, id);

        return map;
    }
}
