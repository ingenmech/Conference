package com.epam.evm.conference.model;

public class Topic {

    private final Long id;
    private final Long sectionId;
    private final Long userId;
    private final String name;
    private final String status;

    private Conference conference;
    private Section section;
    private User user;

    public Topic(Long id, Long sectionId, Long userId, String name, String status) {
        this.id = id;
        this.sectionId = sectionId;
        this.userId = userId;
        this.name = name;
        this.status = status;
    }

    public Long getId() {
        return id;
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
    public String toString() {
        return "Topic{" +
                "id=" + id +
                ", sectionId=" + sectionId +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", conference=" + conference +
                ", section=" + section +
                ", user=" + user +
                '}';
    }
}
