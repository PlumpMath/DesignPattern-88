package com.forum.webapp.dao;

import java.util.List;

import com.forum.webapp.entities.MessageEntity;

public interface IMessageDao extends IDao<MessageEntity> {

    Long create(MessageEntity entity);

    MessageEntity get(Long id);

    List<MessageEntity> list(Long topicId);

    List<MessageEntity> listAll();
}
