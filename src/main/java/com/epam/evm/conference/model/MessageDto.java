package com.epam.evm.conference.model;

import java.util.Objects;

public class MessageDto {

    private final String content;
    private final String userLogin;

    public MessageDto(String content, String userLogin) {
        this.content = content;
        this.userLogin = userLogin;
    }

    public String getContent() {
        return content;
    }

    public String getUserLogin() {
        return userLogin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MessageDto that = (MessageDto) o;
        return Objects.equals(content, that.content) && Objects.equals(userLogin, that.userLogin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, userLogin);
    }

    @Override
    public String toString() {
        return "MessageDto{" +
                "content='" + content + '\'' +
                ", userLogin='" + userLogin + '\'' +
                '}';
    }
}
