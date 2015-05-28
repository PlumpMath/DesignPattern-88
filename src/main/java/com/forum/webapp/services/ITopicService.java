package com.forum.webapp.services;

import java.util.List;

import com.forum.webapp.web.models.Topic;

public interface ITopicService extends IService<Topic> {

    Long create(Topic entity);

    Topic get(Long id);

    List<Topic> list(Long user);

}
