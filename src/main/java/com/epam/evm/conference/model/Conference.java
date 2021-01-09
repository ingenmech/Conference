package com.epam.evm.conference.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Conference implements Entity {

    private final Long id;
    private final String name;
    private final LocalDateTime date;
    private final List<Section> sections;

    public Conference(Long id, String name, LocalDateTime date) {
        this.id = id;
        this.name = name;
        this.date = date;
        sections = new ArrayList<>();
    }

    public Conference(Long id, String name, LocalDateTime date, List<Section> sections) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.sections = sections;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void addSection(Section section) {
        sections.add(section);
    }

    public Section getSection(int index) {
        return sections.get(index);
    }

    public int sizeSections() {
        return sections.size();
    }

    public List<Section> getSections() {
        return sections;
    }


    @Override
    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Conference that = (Conference) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        return sections != null ? sections.equals(that.sections) : that.sections == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (sections != null ? sections.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Conference{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", sections=" + sections +
                '}';
    }
}
