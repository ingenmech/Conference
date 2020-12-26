package com.epam.evm.conference.model;

import java.util.List;

public class Question extends DatabaseEntity {

    private final Long userId;
    private final String content;

    private String userLogin;
    private List<Message> messages;

    public Question(Long id, Long userId, String content) {
        super(id);
        this.userId = userId;
        this.content = content;
    }

    public Question(Long id, Long userId, String content, String userLogin) {
        super(id);
        this.userId = userId;
        this.content = content;
        this.userLogin = userLogin;
    }

    public Long getUserId() {
        return userId;
    }

    public String getContent() {
        return content;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Question question = (Question) o;

        if (userId != null ? !userId.equals(question.userId) : question.userId != null) return false;
        if (content != null ? !content.equals(question.content) : question.content != null) return false;
        if (userLogin != null ? !userLogin.equals(question.userLogin) : question.userLogin != null) return false;
        return messages != null ? messages.equals(question.messages) : question.messages == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (userLogin != null ? userLogin.hashCode() : 0);
        result = 31 * result + (messages != null ? messages.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Question{" +
                "userId=" + userId +
                ", content='" + content + '\'' +
                ", userLogin='" + userLogin + '\'' +
                ", messages=" + messages +
                "} " + super.toString();
    }
}
