package com.forum.webapp.entities;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity
@Table(name = "messages")
@NamedQueries(value = {
        @NamedQuery(name = "listMessages", query = "from MessageEntity where topicId = :topicId order by id"),
@NamedQuery(name = "listAllMessages", query = "from MessageEntity order by id")})
public class MessageEntity implements IEntity {

    private Long _id;

    private String _text;

    private Calendar _dateAndTime;

    private Long _ownerId;

    private Long _topicId;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return _id;
    }

    public void setId(final Long id) {
        _id = id;
    }

    @Column(name = "text", length = 5000, nullable = false)
    public String getText() {
        return _text;
    }

    public void setText(final String text) {
        this._text = text;
    }

    @Column(name = "dateAndTime", nullable = false)
    public Calendar getDateAndTime() {
		return _dateAndTime;
	}

	public void setDateAndTime(Calendar dateAndTime) {
		_dateAndTime = dateAndTime;
	}

	@Column(name = "owner", nullable = false)
    public Long getOwnerId() {
        return _ownerId;
    }

    public void setOwnerId(final Long userId) {
        _ownerId = userId;
    }

    @Column(name = "topic", nullable = false)
	public Long getTopicId() {
		return _topicId;
	}

	public void setTopicId(Long topic) {
		_topicId = topic;
	}

}
