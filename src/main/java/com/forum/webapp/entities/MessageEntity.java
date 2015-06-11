package com.forum.webapp.entities;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "messages")
public class MessageEntity implements IEntity {

    private Long id;

    private String text;

    private Calendar dateAndTime;

    private Long ownerId;

    private Long topicId;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    @Column(length = 5000, nullable = false)
    public String getText() {
        return text;
    }

    public void setText(final String text) {
        this.text = text;
    }

    @Column(nullable = false)
    public Calendar getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(Calendar dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    @Column(nullable = false)
    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(final Long userId) {
        ownerId = userId;
    }

    @Column(nullable = false)
    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topic) {
        this.topicId = topic;
    }

}
