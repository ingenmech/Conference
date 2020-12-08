package com.epam.evm.conference.model;

import java.time.LocalDateTime;
import java.util.List;

public class Conference {

    private final Long id;
    private final String name;
    private final LocalDateTime date;
    private final List<String> sections;

    public Conference(Long id, String name, LocalDateTime date, List<String> sections) {
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

    public List<String> getSections() {
        return sections;
    }
}
