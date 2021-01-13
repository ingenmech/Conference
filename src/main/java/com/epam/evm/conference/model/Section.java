package com.epam.evm.conference.model;

public class Section implements Entity {

    private final Long id;
    private Long conferenceId;
    private final String name;
    private final SectionStatus status;

    public Section(Long id, Long conferenceId, String name, SectionStatus status) {
        this.id = id;
        this.conferenceId = conferenceId;
        this.name = name;
        this.status = status;
    }

    public void setConferenceId(Long conferenceId) {
        this.conferenceId = conferenceId;
    }

    public Long getConferenceId() {
        return conferenceId;
    }

    public String getName() {
        return name;
    }

    public SectionStatus getStatus() {
        return status;
    }

    @Override
    public Long getId() {
        return id;
    }



}

