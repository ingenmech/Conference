package com.epam.evm.conference.model;

import java.util.Objects;

public class Question implements Entity{

    private final Long id;
    private final Long userId;
    private final Long conferenceId;
    private final String content;

    private String userLogin;
    private String conferenceName;

    public Question(Long id, Long userId, Long conferenceId, String content) {
        this.id = id;
        this.userId = userId;
        this.conferenceId = conferenceId;
        this.content = content;
    }

    public Question(Long id, Long userId, Long conferenceId, String content, String userLogin, String conferenceName) {
        this.id = id;
        this.userId = userId;
        this.conferenceId = conferenceId;
        this.content = content;
        this.userLogin = userLogin;
        this.conferenceName = conferenceName;
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

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getConferenceName() {
        return conferenceName;
    }

    public void setConferenceName(String conferenceName) {
        this.conferenceName = conferenceName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return Objects.equals(id, question.id) && Objects.equals(userId, question.userId) && Objects.equals(conferenceId, question.conferenceId) && Objects.equals(content, question.content) && Objects.equals(userLogin, question.userLogin) && Objects.equals(conferenceName, question.conferenceName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, conferenceId, content, userLogin, conferenceName);
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", userId=" + userId +
                ", conferenceId=" + conferenceId +
                ", content='" + content + '\'' +
                ", userLogin='" + userLogin + '\'' +
                ", conferenceName='" + conferenceName + '\'' +
                '}';
    }

    @Override
    public Long getId() {
        return id;
    }
}
