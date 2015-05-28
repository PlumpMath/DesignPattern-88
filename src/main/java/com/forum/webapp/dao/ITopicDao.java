package com.forum.webapp.dao;

import java.util.List;

import com.forum.webapp.entities.TopicEntity;

public interface ITopicDao extends IDao<TopicEntity> {

    Long create(TopicEntity entity);

    void delete(Long id);

    TopicEntity get(Long id);

    List<TopicEntity> list(Long user);

}
