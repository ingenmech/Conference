package com.epam.evm.conference.model;

//TODO equals and hashcode
public class Section {

    private final Long id;
    private Long conferenceId;
    private final String name;

    public Section(Long id, Long conferenceId, String name) {
        this.id = id;
        this.conferenceId = conferenceId;
        this.name = name;
    }

    public void setConferenceId(Long conferenceId) {
        this.conferenceId = conferenceId;
    }

    public Long getId() {
        return id;
    }

    public Long getConferenceId() {
        return conferenceId;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Section{" +
                "id=" + id +
                ", conferenceId=" + conferenceId +
                ", name='" + name + '\'' +
                '}';
    }
}
