package com.epam.evm.conference.dao.extractor;

import com.epam.evm.conference.model.Conference;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class ConferenceFieldExtractor implements FieldExtractor<Conference> {

    private final static int NAME_CONFERENCE = 1;
    private final static int DATE_CONFERENCE = 2;

    @Override
    public Map<Integer, Object> extractForSave(Conference entity) {

        String name = entity.getName();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        LocalDateTime dateTime = entity.getDate();
        String rowDateTime = dateTime.format(formatter);

        Map<Integer, Object> map = new HashMap<>();
        map.put(NAME_CONFERENCE, name);
        map.put(DATE_CONFERENCE, rowDateTime);

        return map;
    }

    @Override
    public Map<Integer, Object> extractForUpdate(Conference entity) {
        return null;
    }
}
