package com.epam.evm.conference.model;

public class Section extends DatabaseEntity{

    private Long conferenceId;
    private final String name;

    public Section(Long id, Long conferenceId, String name) {
        super(id);
        this.conferenceId = conferenceId;
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        if (!super.equals(o)){
            return false;
        }

        Section section = (Section) o;

        if (conferenceId != null ? !conferenceId.equals(section.conferenceId) : section.conferenceId != null)
            return false;
        return name != null ? name.equals(section.name) : section.name == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (conferenceId != null ? conferenceId.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Section{" +
                "conferenceId=" + conferenceId +
                ", name='" + name + '\'' +
                "} " + super.toString();
    }
}
