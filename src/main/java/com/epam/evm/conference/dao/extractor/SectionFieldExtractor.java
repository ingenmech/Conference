package com.epam.evm.conference.dao.extractor;

import com.epam.evm.conference.model.Section;

import java.util.HashMap;
import java.util.Map;

public class SectionFieldExtractor implements FieldExtractor<Section> {

    private final static String CONFERENCE_ID = "conference_id";
    private final static String NAME = "name";

    @Override
    public Map<String, Object> extract(Section entity) {

        String name = entity.getName();
        Long conferenceId = entity.getConferenceId();

        Map<String, Object> sectionFields = new HashMap<>();
        sectionFields.put(CONFERENCE_ID, conferenceId);
        sectionFields.put(NAME, name);

        return sectionFields;
    }
}
