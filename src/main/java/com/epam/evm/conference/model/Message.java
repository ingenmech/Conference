package com.epam.evm.conference.model;

import java.time.LocalDateTime;

public class Message extends DatabaseEntity {

    private final Long questionId;
    private final Long userId;
    private final LocalDateTime dateTime;
    private final String content;

    public Message(Long id, Long questionId, Long userId, LocalDateTime dateTime, String content) {
        super(id);
        this.questionId = questionId;
        this.userId = userId;
        this.dateTime = dateTime;
        this.content = content;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public Long getUserId() {
        return userId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getContent() {
        return content;
    }
}
