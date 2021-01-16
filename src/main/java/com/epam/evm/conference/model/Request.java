package com.epam.evm.conference.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Request implements Entity {

    private final Long id;
    private final Long sectionId;
    private final Long userId;
    private final String topic;
    private final RequestStatus status;

    private String conferenceName;
    private String sectionName;
    private String userLogin;
    private SectionStatus sectionStatus;
    private LocalDateTime conferenceDate;

    public Request(Long id, Long sectionId, Long userId, String topic, RequestStatus status) {
        this.id = id;
        this.sectionId = sectionId;
        this.userId = userId;
        this.topic = topic;
        this.status = status;
    }

    public Request(Long id, Long sectionId, Long userId, String topic, RequestStatus status,
                   String conferenceName, String sectionName, String userLogin,
                   SectionStatus sectionStatus, LocalDateTime conferenceDate) {
        this.id = id;
        this.sectionId = sectionId;
        this.userId = userId;
        this.topic = topic;
        this.status = status;
        this.conferenceName = conferenceName;
        this.sectionName = sectionName;
        this.userLogin = userLogin;
        this.sectionStatus = sectionStatus;
        this.conferenceDate = conferenceDate;
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

    public SectionStatus getSectionStatus() {
        return sectionStatus;
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

    public void setSectionStatus(SectionStatus sectionStatus) {
        this.sectionStatus = sectionStatus;
    }

    public LocalDateTime getConferenceDate() {
        return conferenceDate;
    }

    public void setConferenceDate(LocalDateTime conferenceDate) {
        this.conferenceDate = conferenceDate;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Request request = (Request) o;
        return Objects.equals(id, request.id) && Objects.equals(sectionId, request.sectionId) && Objects.equals(userId, request.userId) && Objects.equals(topic, request.topic) && status == request.status && Objects.equals(conferenceName, request.conferenceName) && Objects.equals(sectionName, request.sectionName) && Objects.equals(userLogin, request.userLogin) && sectionStatus == request.sectionStatus && Objects.equals(conferenceDate, request.conferenceDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sectionId, userId, topic, status, conferenceName, sectionName, userLogin, sectionStatus, conferenceDate);
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
                ", sectionStatus=" + sectionStatus +
                ", conferenceDate=" + conferenceDate +
                '}';
    }
}
