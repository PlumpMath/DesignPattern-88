package com.forum.webapp.web.models;

import java.util.Date;

import com.forum.webapp.entities.MessageEntity;

public class Message implements IModel {

    private Long id;

    private String text;

    private Date dateAndTime;

    private Long ownerId;

    private Long topicId;

    private User owner;

    public Message() {
        super();
    }

    public Message(final MessageEntity entity) {
        id = entity.getId();
        text = entity.getText();
        dateAndTime = entity.getDateAndTime().getTime();
        ownerId = entity.getOwnerId();
        topicId = entity.getTopicId();
    }

    public long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDateAndTime() {
        return dateAndTime;
    }

    public void setOwnerId(final Long userId) {
        this.ownerId = userId;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(final Long topicId) {
        this.topicId = topicId;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(final User user) {
        this.owner = user;
    }

    public MessageEntity toEntity() {
        MessageEntity result = new MessageEntity();
        result.setId(id);
        result.setText(text);
        result.setOwnerId(ownerId);
        result.setTopicId(topicId);

        return result;
    }
}
