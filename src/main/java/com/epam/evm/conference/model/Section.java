package com.epam.evm.conference.model;

public class Section {

    private final Long id;
    private final Long conferenceId;
    private final String topic;

    public Section(Long id, Long conferenceId, String topic) {
        this.id = id;
        this.conferenceId = conferenceId;
        this.topic = topic;
    }

    public String getTopic() {
        return topic;
    }

}
