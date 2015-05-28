package com.forum.webapp.dao.impl;

import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.forum.webapp.dao.IMessageDao;
import com.forum.webapp.entities.MessageEntity;

@Transactional
@Repository("messageDao")
public class MessageDao extends CustomHibernateDaoSupport implements IMessageDao {

    public Long create(final MessageEntity entity) {
        entity.setDateAndTime(new GregorianCalendar());
        getSession().save(entity);
        return entity.getId();
    }

    public MessageEntity get(final Long id) {
        return (MessageEntity) getSession().get(MessageEntity.class, id);
    }

    public List<MessageEntity> list(final Long topicId) {
        final Query query = getSession().getNamedQuery("listMessages").setLong("topicId", topicId);
        @SuppressWarnings("unchecked")
        final List<MessageEntity> messages = (List<MessageEntity>) query.list();
        final List<MessageEntity> result = new LinkedList<MessageEntity>();
        for (MessageEntity message : messages) {
            result.add(message);
        }
        return result;
    }

    public List<MessageEntity> listAll() {
        final Query query = getSession().getNamedQuery("listAllMessages");
        @SuppressWarnings("unchecked")
        final List<MessageEntity> messages = (List<MessageEntity>) query.list();
        final List<MessageEntity> result = new LinkedList<MessageEntity>();
        for (MessageEntity message : messages) {
            result.add(message);
        }
        return result;
    }

}
