package com.epam.evm.conference.dao.extractor;

import com.epam.evm.conference.model.Section;
import com.epam.evm.conference.model.SectionStatus;

import java.util.HashMap;
import java.util.Map;

public class SectionFieldExtractor implements FieldExtractor<Section> {

    private final static String CONFERENCE_ID = "conference_id";
    private final static String NAME = "name";
    private final static String STATUS = "status";

    @Override
    public Map<String, Object> extract(Section entity) {

        String name = entity.getName();
        Long conferenceId = entity.getConferenceId();
        SectionStatus status = entity.getStatus();

        Map<String, Object> sectionFields = new HashMap<>();
        sectionFields.put(CONFERENCE_ID, conferenceId);
        sectionFields.put(NAME, name);
        sectionFields.put(STATUS, status.toString());

        return sectionFields;
    }
}
