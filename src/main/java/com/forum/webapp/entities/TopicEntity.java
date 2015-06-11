package com.forum.webapp.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity
@Table(name = "topics")
@NamedQueries(value = { @NamedQuery(name = "listTopics", query = "from TopicEntity e where creatorId = :user or publicTopic = true or "
        + "exists (select 1 from ShareEntity where topicId = e.id and readerId = :user)") })
public class TopicEntity implements IEntity {

    private Long id;

    private String title;

    private Long creatorId;

    private Boolean publicTopic = Boolean.TRUE;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    @Column(length = 200, nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    @Column(nullable = false)
    public Boolean getPublicTopic() {
        return publicTopic;
    }

    public void setPublicTopic(Boolean publicTopic) {
        this.publicTopic = publicTopic;
    }

}
