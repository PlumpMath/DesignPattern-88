package com.forum.webapp.repos;

import org.springframework.data.repository.CrudRepository;

import com.forum.webapp.entities.TopicEntity;

public interface TopicRepository extends CrudRepository<TopicEntity, Long> {

}
