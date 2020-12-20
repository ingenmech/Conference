package com.epam.evm.conference.dao.extractor;

import com.epam.evm.conference.model.Section;

import java.util.HashMap;
import java.util.Map;

public class SectionFieldExtractor implements FieldExtractor<Section> {

    private final static int CONFERENCE_ID = 1;
    private final static int NAME = 2;

    @Override
    public Map<Integer, Object> extract(Section entity) {

        String name = entity.getName();
        Long conferenceId = entity.getConferenceId();

        Map<Integer, Object> sectionFields = new HashMap<>();
        sectionFields.put(CONFERENCE_ID, conferenceId);
        sectionFields.put(NAME, name);

        return sectionFields;
    }
}
