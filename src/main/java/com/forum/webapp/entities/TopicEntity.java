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
@NamedQueries(value = {
		@NamedQuery(name = "listTopics", query = "from TopicEntity e where public = true or "
        + "exists (select 1 from ShareEntity where topic = e.id and reader = :user)") })
public class TopicEntity implements IEntity {

	private Long _id;

	private String _title;

	private Boolean _public = Boolean.TRUE;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return _id;
	}

	public void setId(final Long id) {
		_id = id;
	}

	@Column(name = "title", length = 200, nullable = false)
	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		_title = title;
	}

	@Column(name = "public", nullable = false)
	public Boolean isPublic() {
		return _public;
	}

	public void setPublic(Boolean isPublic) {
		_public = isPublic;
	}

}
