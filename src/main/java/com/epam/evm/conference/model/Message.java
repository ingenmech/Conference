package com.epam.evm.conference.model;


import java.util.Objects;

public class Message implements Entity {

    private final Long id;
    private final Long questionId;
    private final Long userId;
    private final String content;

    public Message(Long id, Long questionId, Long userId, String content) {
        this.id = id;
        this.questionId = questionId;
        this.userId = userId;
        this.content = content;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public Long getUserId() {
        return userId;
    }

    public String getContent() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Message message = (Message) o;
        return Objects.equals(id, message.id) && Objects.equals(questionId, message.questionId) && Objects.equals(userId, message.userId) && Objects.equals(content, message.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, questionId, userId, content);
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", questionId=" + questionId +
                ", userId=" + userId +
                ", content='" + content + '\'' +
                '}';
    }

    @Override
    public Long getId() {
        return id;
    }
}
