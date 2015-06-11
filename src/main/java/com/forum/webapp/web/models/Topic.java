package com.forum.webapp.web.models;

import com.forum.webapp.entities.TopicEntity;

public class Topic implements IModel {

    private Long id;

    private String title;

    private Long creatorId;

    private boolean publicTopic = true;

    public Topic() {
        super();
    }

    public Topic(final TopicEntity entity) {
        id = entity.getId();
        title = entity.getTitle();
        publicTopic = entity.getPublicTopic();
        creatorId = entity.getCreatorId();
    }

    public long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String label) {
        this.title = label;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public boolean isPublicTopic() {
        return publicTopic;
    }

    public void setPublicTopic(boolean publicTopic) {
        this.publicTopic = publicTopic;
    }

    public TopicEntity toEntity() {
        TopicEntity result = new TopicEntity();
        result.setId(id);
        result.setTitle(title);
        result.setPublicTopic(publicTopic);
        result.setCreatorId(creatorId);
        return result;
    }

}
