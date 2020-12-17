package com.epam.evm.conference.model;

public class Question extends DatabaseEntity {

    private final Long userId;
    private final String content;

    public Question(Long id, Long userId, String content) {
        super(id);
        this.userId = userId;
        this.content = content;
    }

    public Long getUserId() {
        return userId;
    }

    public String getContent() {
        return content;
    }
}
