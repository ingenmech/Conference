package com.epam.evm.conference.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//TODO equals and hashcode
public class Conference {

    private final Long id;
    private final String name;
    private final LocalDateTime date;
    private List<Section> sections;

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

    public Long getId() {
        return id;
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
