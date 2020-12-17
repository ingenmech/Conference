package com.epam.evm.conference.model;

public class Request extends DatabaseEntity {

    private final Long sectionId;
    private final Long userId;
    private final String topic;
    private final String status;

    private Conference conference;
    private Section section;
    private User user;

    public Request(Long id, Long sectionId, Long userId, String topic, String status) {
        super(id);
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

    public String getStatus() {
        return status;
    }

    public Conference getConference() {
        return conference;
    }

    public void setConference(Conference conference) {
        this.conference = conference;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        Request request = (Request) o;

        if (sectionId != null ? !sectionId.equals(request.sectionId) : request.sectionId != null) {
            return false;
        }
        if (userId != null ? !userId.equals(request.userId) : request.userId != null) {
            return false;
        }
        if (topic != null ? !topic.equals(request.topic) : request.topic != null) {
            return false;
        }
        if (status != null ? !status.equals(request.status) : request.status != null) {
            return false;
        }
        if (conference != null ? !conference.equals(request.conference) : request.conference != null) {
            return false;
        }
        if (section != null ? !section.equals(request.section) : request.section != null) {
            return false;
        }
        return user != null ? user.equals(request.user) : request.user == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (sectionId != null ? sectionId.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (topic != null ? topic.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (conference != null ? conference.hashCode() : 0);
        result = 31 * result + (section != null ? section.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "sectionId=" + sectionId +
                ", userId=" + userId +
                ", name='" + topic + '\'' +
                ", status='" + status + '\'' +
                ", conference=" + conference +
                ", section=" + section +
                ", user=" + user +
                "} " + super.toString();
    }
}
