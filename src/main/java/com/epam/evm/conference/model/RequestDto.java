package com.epam.evm.conference.model;

public class RequestDto extends DatabaseEntity{

    private final String topic;
    private final RequestStatus status;

    private final String conferenceName;
    private final String sectionName;
    private final String userLogin;

    public RequestDto(Long id, String topic, RequestStatus status,
                      String conferenceName, String sectionName, String userLogin) {
        super(id);
        this.topic = topic;
        this.status = status;
        this.conferenceName = conferenceName;
        this.sectionName = sectionName;
        this.userLogin = userLogin;
    }

    public String getTopic() {
        return topic;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public String getConferenceName() {
        return conferenceName;
    }

    public String getSectionName() {
        return sectionName;
    }

    public String getUserLogin() {
        return userLogin;
    }
}
