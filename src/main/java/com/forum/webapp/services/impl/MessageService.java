package com.forum.webapp.services.impl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.forum.webapp.dao.IMessageDao;
import com.forum.webapp.entities.MessageEntity;
import com.forum.webapp.services.IMessageService;
import com.forum.webapp.web.models.Message;

@Service("messageService")
public class MessageService implements IMessageService {

    private IMessageDao messageDao;

    @Autowired
    public void setMessageDao(IMessageDao messageDao) {
        this.messageDao = messageDao;
    }

    @Transactional
    public Long create(final Message entity) {
        return messageDao.create(entity.toEntity());
    }

    @Transactional
    public Message get(final Long id) {
        return new Message(messageDao.get(id));
    }

    @Transactional
    public List<Message> list(final Long topicId) {
        final List<MessageEntity> entities = messageDao.list(topicId);
        final List<Message> result = new LinkedList<Message>();
        for (MessageEntity entity : entities) {
            result.add(new Message(entity));
        }
        return result;
    }

}
