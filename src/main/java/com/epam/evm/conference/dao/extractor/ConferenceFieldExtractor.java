package com.epam.evm.conference.dao.extractor;

import com.epam.evm.conference.model.Conference;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class ConferenceFieldExtractor implements FieldExtractor<Conference> {

    private final static String NAME = "name";
    private final static String DATE = "date";

    @Override
    public Map<String, Object> extract(Conference entity) {

        String name = entity.getName();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        LocalDateTime dateTime = entity.getDate();
        String rowDateTime = dateTime.format(formatter);

        Map<String, Object> map = new HashMap<>();
        map.put(NAME, name);
        map.put(DATE, rowDateTime);

        return map;
    }
}
