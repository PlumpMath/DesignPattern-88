package com.forum.webapp.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.forum.webapp.dao.ITopicDao;
import com.forum.webapp.entities.TopicEntity;
import com.forum.webapp.repos.TopicRepository;

@Transactional
@Component
public class TopicDao implements ITopicDao {

	@Autowired
	private TopicRepository repository;

	@Autowired
	private EntityManager entityManager;

	public Long create(final TopicEntity entity) {
		repository.save(entity);
		return entity.getId();
	}

	public TopicEntity get(final Long id) {
		return repository.findOne(id);
	}

	public void delete(final Long id) {
		repository.delete(id);
	}

	@SuppressWarnings("unchecked")
	public List<TopicEntity> list(Long user) {
		final Query query = entityManager.createNamedQuery("listTopics",
		        TopicEntity.class);
		query.setParameter("user", user);
		return query.getResultList();
	}

}
