package com.epam.evm.conference.model;

public class Request implements Entity {

    private final Long id;
    private final Long sectionId;
    private final Long userId;
    private final String topic;
    private final RequestStatus status;

    private String conferenceName;
    private String sectionName;
    private String userLogin;

    public Request(Long id, Long sectionId, Long userId, String topic, RequestStatus status) {
        this.id = id;
        this.sectionId = sectionId;
        this.userId = userId;
        this.topic = topic;
        this.status = status;
    }

    public Request(Long id, Long sectionId, Long userId, String topic, RequestStatus status,
    String conferenceName, String sectionName, String userLogin) {
        this.id = id;
        this.sectionId = sectionId;
        this.userId = userId;
        this.topic = topic;
        this.status = status;
        this.conferenceName = conferenceName;
        this.sectionName = sectionName;
        this.userLogin = userLogin;
    }

    public Long getSectionId() {
        return sectionId;
    }

    public Long getUserId() {
        return userId;
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

    public void setConferenceName(String conferenceName) {
        this.conferenceName = conferenceName;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }


    @Override
    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        Request request = (Request) o;
        if (id != null ? !id.equals(request.id) : request.id != null){
            return false;
        }
        if (sectionId != null ? !sectionId.equals(request.sectionId) : request.sectionId != null){
            return false;
        }
        if (userId != null ? !userId.equals(request.userId) : request.userId != null){
            return false;
        }
        if (topic != null ? !topic.equals(request.topic) : request.topic != null){
            return false;
        }
        if (status != request.status){
            return false;
        }
        if (conferenceName != null ? !conferenceName.equals(request.conferenceName) : request.conferenceName != null){
            return false;
        }
        if (sectionName != null ? !sectionName.equals(request.sectionName) : request.sectionName != null){
            return false;
        }
        return userLogin != null ? userLogin.equals(request.userLogin) : request.userLogin == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (sectionId != null ? sectionId.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (topic != null ? topic.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (conferenceName != null ? conferenceName.hashCode() : 0);
        result = 31 * result + (sectionName != null ? sectionName.hashCode() : 0);
        result = 31 * result + (userLogin != null ? userLogin.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", sectionId=" + sectionId +
                ", userId=" + userId +
                ", topic='" + topic + '\'' +
                ", status=" + status +
                ", conferenceName='" + conferenceName + '\'' +
                ", sectionName='" + sectionName + '\'' +
                ", userLogin='" + userLogin + '\'' +
                '}';
    }
}
