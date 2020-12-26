package com.epam.evm.conference.model;

import java.time.LocalDateTime;

public class Message extends DatabaseEntity {

    private final Long questionId;
    private final Long userId;
    private final LocalDateTime dateTime;
    private final String content;

    private String userLogin;

    public Message(Long id, Long questionId, Long userId, LocalDateTime dateTime, String content) {
        super(id);
        this.questionId = questionId;
        this.userId = userId;
        this.dateTime = dateTime;
        this.content = content;
    }

    public Message(Long id, Long questionId, Long userId, LocalDateTime dateTime, String content, String userLogin) {
        super(id);
        this.questionId = questionId;
        this.userId = userId;
        this.dateTime = dateTime;
        this.content = content;
        this.userLogin = userLogin;
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

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Message message = (Message) o;

        if (questionId != null ? !questionId.equals(message.questionId) : message.questionId != null) return false;
        if (userId != null ? !userId.equals(message.userId) : message.userId != null) return false;
        if (dateTime != null ? !dateTime.equals(message.dateTime) : message.dateTime != null) return false;
        if (content != null ? !content.equals(message.content) : message.content != null) return false;
        return userLogin != null ? userLogin.equals(message.userLogin) : message.userLogin == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (questionId != null ? questionId.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (dateTime != null ? dateTime.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (userLogin != null ? userLogin.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Message{" +
                "questionId=" + questionId +
                ", userId=" + userId +
                ", dateTime=" + dateTime +
                ", content='" + content + '\'' +
                ", userLogin='" + userLogin + '\'' +
                "} " + super.toString();
    }
}
