package com.epam.evm.conference.model;

import java.util.Objects;

public class RequestDto {

    private final Long id;
    private final Long sectionId;
    private final Long userId;
    private final String topic;
    private final RequestStatus status;

    private final String conferenceName;
    private final String sectionName;
    private final SectionStatus sectionStatus;
    private final String userLogin;

    public RequestDto(Long id, Long sectionId, Long userId, String topic, RequestStatus status,
                   String conferenceName, String sectionName, SectionStatus sectionStatus, String userLogin) {
        this.id = id;
        this.sectionId = sectionId;
        this.userId = userId;
        this.topic = topic;
        this.status = status;
        this.conferenceName = conferenceName;
        this.sectionName = sectionName;
        this.userLogin = userLogin;
        this.sectionStatus = sectionStatus;
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

    public Long getId() {
        return id;
    }

    public SectionStatus getSectionStatus() {
        return sectionStatus;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestDto that = (RequestDto) o;
        return Objects.equals(id, that.id) && Objects.equals(sectionId, that.sectionId) && Objects.equals(userId, that.userId) && Objects.equals(topic, that.topic) && status == that.status && Objects.equals(conferenceName, that.conferenceName) && Objects.equals(sectionName, that.sectionName) && sectionStatus == that.sectionStatus && Objects.equals(userLogin, that.userLogin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sectionId, userId, topic, status, conferenceName, sectionName, sectionStatus, userLogin);
    }
}
