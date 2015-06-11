package com.forum.webapp.services;

import java.util.List;

import com.forum.webapp.web.models.Message;

public interface IMessageService extends IService<Message> {

    Long create(Message entity);

    Message get(Long id);

    List<Message> list(Long topicId);

}
