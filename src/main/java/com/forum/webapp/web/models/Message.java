package com.forum.webapp.web.models;

import java.util.Date;

import org.hibernate.validator.NotNull;
import org.hibernate.validator.Size;

import com.forum.webapp.entities.MessageEntity;

public class Message implements IModel {

    private Long _id;

    private String _text;

    private Date _dateAndTime;

    private Long _ownerId;

    private Long _topicId;

    private User _owner;

    public Message() {
        super();
    }

    public Message(final MessageEntity entity) {
        _id = entity.getId();
        _text = entity.getText();
        _dateAndTime = entity.getDateAndTime().getTime();
        _ownerId = entity.getOwnerId();
        _topicId = entity.getTopicId();
    }

    public long getId() {
        return _id;
    }

    public void setId(final Long id) {
        _id = id;
    }

    public String getText() {
        return _text;
    }

    public void setText(String text) {
        _text = text;
    }

    public Date getDateAndTime() {
        return _dateAndTime;
    }

    public void setOwnerId(final Long userId) {
        _ownerId = userId;
    }

    public Long getOwnerId() {
        return _ownerId;
    }

    public Long getTopicId() {
        return _topicId;
    }

    public void setTopicId(final Long topicId) {
        _topicId = topicId;
    }

    public User getOwner() {
        return _owner;
    }

    public void setOwner(final User user) {
        _owner = user;
    }

    public MessageEntity toEntity() {
        MessageEntity result = new MessageEntity();
        result.setId(_id);
        result.setText(_text);
        result.setOwnerId(_ownerId);
        result.setTopicId(_topicId);

        return result;
    }
}
