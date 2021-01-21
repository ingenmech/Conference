package com.epam.evm.conference.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Request implements Entity {

    private final Long id;
    private final Long sectionId;
    private final Long userId;
    private final String topic;
    private final RequestStatus status;

    public Request(Long id, Long sectionId, Long userId, String topic, RequestStatus status) {
        this.id = id;
        this.sectionId = sectionId;
        this.userId = userId;
        this.topic = topic;
        this.status = status;
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
        return Objects.equals(id, request.id) && Objects.equals(sectionId, request.sectionId) && Objects.equals(userId, request.userId) && Objects.equals(topic, request.topic) && status == request.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sectionId, userId, topic, status);
    }

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", sectionId=" + sectionId +
                ", userId=" + userId +
                ", topic='" + topic + '\'' +
                ", status=" + status +
                '}';
    }
}
