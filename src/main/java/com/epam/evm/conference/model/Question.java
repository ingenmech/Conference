package com.epam.evm.conference.model;

import java.util.Objects;

public class Question implements Entity{

    private final Long id;
    private final Long userId;
    private final Long conferenceId;
    private final String content;

    public Question(Long id, Long userId, Long conferenceId, String content) {
        this.id = id;
        this.userId = userId;
        this.conferenceId = conferenceId;
        this.content = content;
    }

    public Long getUserId() {
        return userId;
    }

    public String getContent() {
        return content;
    }

    public Long getConferenceId() {
        return conferenceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Question question = (Question) o;
        return Objects.equals(id, question.id) && Objects.equals(userId, question.userId) && Objects.equals(conferenceId, question.conferenceId) && Objects.equals(content, question.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, conferenceId, content);
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", userId=" + userId +
                ", conferenceId=" + conferenceId +
                ", content='" + content + '\'' +
                '}';
    }

    @Override
    public Long getId() {
        return id;
    }
}
