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

    public Long getUserId() {
        return userId;
    }

    public String getContent() {
        return content;
    }

    public Message getMessage(int index){
        return messages.get(index);
    }

    public void addMessage(Message message){
        messages.add(message);
    }

    public int messageSize(){
        return messages.size();
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }
}
