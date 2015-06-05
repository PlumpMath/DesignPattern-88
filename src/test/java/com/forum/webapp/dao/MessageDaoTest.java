package com.forum.webapp.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.forum.webapp.entities.MessageEntity;
import com.forum.webapp.entities.TopicEntity;
import com.forum.webapp.entities.UserEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:test-applicationContext.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class MessageDaoTest {

    private Long topicId;
    private Long userId;

    @Autowired
    protected IMessageDao messageDao;

    @Autowired
    protected ITopicDao topicDao;

    @Autowired
    protected IUserDao userDao;

    @Before
    public void setup() {
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName("First");
        userEntity.setName("Name");
        userEntity.setEmail("toto@titi.com");
        userEntity.setPassword("pwd");
        userId = userDao.create(userEntity);

        TopicEntity topicEntity = new TopicEntity();
        topicEntity.setPublicTopic(true);
        topicEntity.setTitle("Title");
        topicEntity.setCreatorId(userId);
        topicId = topicDao.create(topicEntity);
    }

    @After
    public void teardown() {
        userDao.delete(userId);
        topicDao.delete(topicId);
    }

    @Test
    public void createAndListMessages() {
        assertTrue(messageDao.list(topicId).isEmpty());

        MessageEntity entity = new MessageEntity();
        entity.setText("My Message");
        entity.setOwnerId(userId);
        entity.setTopicId(topicId);

        Long id = messageDao.create(entity);
        assertNotNull(id);
        entity = messageDao.get(id);

        assertNotNull(entity);
        assertEquals("My Message", entity.getText());
        assertEquals(userId, entity.getOwnerId());
        assertEquals(topicId, entity.getTopicId());
        assertNotNull(entity.getDateAndTime());

        assertEquals(1, messageDao.list(topicId).size());
    }

}
