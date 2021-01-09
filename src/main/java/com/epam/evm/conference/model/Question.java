package com.epam.evm.conference.model;

import java.util.List;

public class Question implements Entity{

    private final Long id;
    private final Long userId;
    private final String content;

    private String userLogin;
    private List<Message> messages;

    public Question(Long id, Long userId, String content) {
        this.id = id;
        this.userId = userId;
        this.content = content;
    }

    public Question(Long id, Long userId, String content, String userLogin) {
        this.id = id;
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
        if (o == null || getClass() != o.getClass()){
            return false;
        }

        Question question = (Question) o;

        if (id != null ? !id.equals(question.id) : question.id != null){
            return false;
        }
        if (userId != null ? !userId.equals(question.userId) : question.userId != null) {
            return false;
        }
        if (content != null ? !content.equals(question.content) : question.content != null) {
            return false;
        }
        if (userLogin != null ? !userLogin.equals(question.userLogin) : question.userLogin != null) {
            return false;
        }
        return messages != null ? messages.equals(question.messages) : question.messages == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (userLogin != null ? userLogin.hashCode() : 0);
        result = 31 * result + (messages != null ? messages.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", userId=" + userId +
                ", content='" + content + '\'' +
                ", userLogin='" + userLogin + '\'' +
                ", messages=" + messages +
                '}';
    }

    @Override
    public Long getId() {
        return id;
    }
}
