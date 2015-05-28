package com.forum.webapp.web.models;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.Size;

import com.forum.webapp.entities.TopicEntity;

public class Topic implements IModel {

	private Long _id;

	private String _title;

	private boolean _isPublic;

	public Topic() {
		super();
	}

	public Topic(final TopicEntity entity) {
		_id = entity.getId();
		_title = entity.getTitle();
	}

	public long getId() {
		return _id;
	}

	public void setId(final Long id) {
		_id = id;
	}

	public String getTitle() {
		return _title;
	}

	public void setTitle(String label) {
		this._title = label;
	}

	public boolean isPublic() {
		return _isPublic;
	}

	public void setPublic(boolean isPublic) {
		this._isPublic = isPublic;
	}

	public TopicEntity toEntity() {
		TopicEntity result = new TopicEntity();
		result.setId(_id);
		result.setTitle(_title);
                result.setPublic(_isPublic);
		return result;
	}

}
