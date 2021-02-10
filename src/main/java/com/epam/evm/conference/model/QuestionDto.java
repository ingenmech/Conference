package com.epam.evm.conference.model;


import java.util.Objects;

public class QuestionDto{

    private final Long id;
    private final String content;
    private final String userLogin;
    private final String conferenceName;

    public QuestionDto(Long id, String content, String userLogin, String conferenceName) {
        this.id = id;
        this.content = content;
        this.userLogin = userLogin;
        this.conferenceName = conferenceName;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public String getConferenceName() {
        return conferenceName;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        QuestionDto that = (QuestionDto) o;
        return Objects.equals(id, that.id) && Objects.equals(content, that.content) && Objects.equals(userLogin, that.userLogin) && Objects.equals(conferenceName, that.conferenceName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, userLogin, conferenceName);
    }

    @Override
    public String toString() {
        return "QuestionDto{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", userLogin='" + userLogin + '\'' +
                ", conferenceName='" + conferenceName + '\'' +
                '}';
    }
}
