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
@Table(name = "shares")
@NamedQueries(value = {
                @NamedQuery(name = "canReadTopic", query = "from ShareEntity where topic = :topic and reader = :reader")
})
public class ShareEntity implements IEntity {

	private Long _id;

	private Long _topicId;

	private Long _readerId;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return _id;
	}

	public void setId(final Long id) {
		_id = id;
	}

	@Column(name = "topic")
	public Long getTopic() {
		return _topicId;
	}

	public void setTopic(Long topicId) {
		_topicId = topicId;
	}

	@Column(name = "reader")
	public Long getReader() {
		return _readerId;
	}

	public void setReader(Long readerId) {
		_readerId = readerId;
	}

}
