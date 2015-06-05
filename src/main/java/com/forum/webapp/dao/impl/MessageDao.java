package com.forum.webapp.dao.impl;

import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.forum.webapp.dao.IMessageDao;
import com.forum.webapp.entities.MessageEntity;
import com.forum.webapp.repos.MessageRepository;
import com.google.common.collect.Lists;

@Transactional
@Component
public class MessageDao implements IMessageDao {

    @Autowired
    private MessageRepository messageRepository;

    public void setMessageRepository(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Long create(final MessageEntity entity) {
        entity.setDateAndTime(new GregorianCalendar());
        messageRepository.save(entity);
        return entity.getId();
    }

    public MessageEntity get(final Long id) {
        return messageRepository.findOne(id);
    }

    public List<MessageEntity> list(final Long topicId) {
        return messageRepository.findByTopicIdOrderById(topicId);
    }

    public List<MessageEntity> listAll() {
        return Lists.newLinkedList(messageRepository.findAll());
    }

}
