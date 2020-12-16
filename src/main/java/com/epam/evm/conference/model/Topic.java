package com.epam.evm.conference.model;

public class Topic extends DatabaseEntity {

    private final Long sectionId;
    private final Long userId;
    private final String name;
    private final String status;

    private Conference conference;
    private Section section;
    private User user;

    public Topic(Long id, Long sectionId, Long userId, String name, String status) {
        super(id);
        this.sectionId = sectionId;
        this.userId = userId;
        this.name = name;
        this.status = status;
    }

    public Long getSectionId() {
        return sectionId;
    }

    public Long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
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

        Topic topic = (Topic) o;

        if (sectionId != null ? !sectionId.equals(topic.sectionId) : topic.sectionId != null) {
            return false;
        }
        if (userId != null ? !userId.equals(topic.userId) : topic.userId != null) {
            return false;
        }
        if (name != null ? !name.equals(topic.name) : topic.name != null) {
            return false;
        }
        if (status != null ? !status.equals(topic.status) : topic.status != null) {
            return false;
        }
        if (conference != null ? !conference.equals(topic.conference) : topic.conference != null) {
            return false;
        }
        if (section != null ? !section.equals(topic.section) : topic.section != null) {
            return false;
        }
        return user != null ? user.equals(topic.user) : topic.user == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (sectionId != null ? sectionId.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
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
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", conference=" + conference +
                ", section=" + section +
                ", user=" + user +
                "} " + super.toString();
    }
}
