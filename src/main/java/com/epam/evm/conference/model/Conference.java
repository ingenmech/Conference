package com.epam.evm.conference.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        return new ArrayList<>(sections);
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
        Conference that = (Conference) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(date, that.date) && Objects.equals(sections, that.sections);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, date, sections);
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
