package com.forum.webapp.services.impl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.forum.webapp.dao.ITopicDao;
import com.forum.webapp.entities.TopicEntity;
import com.forum.webapp.services.ITopicService;
import com.forum.webapp.web.models.Topic;

@Service("topicService")
public class TopicService implements ITopicService {

    private ITopicDao topicDao;

    @Autowired
    public void setTopicDao(final ITopicDao topicDao) {
        this.topicDao = topicDao;
    }

    @Transactional
    public Long create(final Topic entity) {
        return topicDao.create(entity.toEntity());
    }

    @Transactional
    public Topic get(final Long id) {
        final TopicEntity entity = topicDao.get(id);
        if (null == entity) {
            return null;
        }
        return new Topic(entity);
    }

    @Transactional
    public List<Topic> list(final Long user) {
        final List<TopicEntity> entities = topicDao.list(user);
        final List<Topic> result = new LinkedList<Topic>();
        for (TopicEntity entity : entities) {
            result.add(new Topic(entity));
        }
        return result;
    }

}
