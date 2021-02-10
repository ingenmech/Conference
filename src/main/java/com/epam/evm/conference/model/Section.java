package com.epam.evm.conference.model;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Section section = (Section) o;
        return Objects.equals(id, section.id) && Objects.equals(conferenceId, section.conferenceId) && Objects.equals(name, section.name) && status == section.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, conferenceId, name, status);
    }

    @Override
    public String toString() {
        return "Section{" +
                "id=" + id +
                ", conferenceId=" + conferenceId +
                ", name='" + name + '\'' +
                ", status=" + status +
                '}';
    }
}

