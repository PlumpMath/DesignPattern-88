package com.forum.webapp.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.forum.webapp.dao.ITopicDao;
import com.forum.webapp.entities.TopicEntity;

@Transactional
@Repository("topicDao")
public class TopicDao extends CustomHibernateDaoSupport implements ITopicDao {

	public Long create(final TopicEntity entity) {
		getSession().save(entity);
		return entity.getId();
	}

	public TopicEntity get(final Long id) {
		return (TopicEntity) getSession().get(TopicEntity.class, id);
	}

	public void delete(final Long id) {
		getSession().delete(get(id));
	}

	@SuppressWarnings("unchecked")
	public List<TopicEntity> list(Long user) {
		final Query query = getSession().getNamedQuery("listTopics").setLong("user", user);
		return (List<TopicEntity>) query.list();
	}

}
