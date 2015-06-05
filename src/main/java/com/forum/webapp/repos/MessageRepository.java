package com.forum.webapp.repos;

import java.util.Collection;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.forum.webapp.entities.MessageEntity;

@Repository
public interface MessageRepository extends CrudRepository<MessageEntity, Long> {

    List<MessageEntity> findByTopicIdOrderById(Long topicId);

    List<MessageEntity> findByTextLikeIgnoreCase(String subject);

    List<MessageEntity> findByTextLikeIgnoreCaseAndOwnerIdIn(String subject,
            Collection<Long> authorIds);
}
