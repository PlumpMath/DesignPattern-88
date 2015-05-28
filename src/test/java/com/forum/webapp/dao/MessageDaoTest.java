package com.forum.webapp.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.forum.webapp.entities.MessageEntity;
import com.forum.webapp.entities.TopicEntity;
import com.forum.webapp.entities.UserEntity;
import org.junit.After;
import org.junit.Before;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:test-applicationContext.xml")
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = false)
public class MessageDaoTest {

    private Long _topicId;
    private Long _userId;
    @Autowired
    @Qualifier("messageDao")
    protected IMessageDao _messageDao;
    @Autowired
    @Qualifier("topicDao")
    protected ITopicDao _topicDao;
    @Autowired
    @Qualifier("userDao")
    protected IUserDao _userDao;

    @Before
    public void setup() {
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName("");
        userEntity.setName("");
        userEntity.setEmail("");
        userEntity.setPassword("");
        _userId = _userDao.create(userEntity);

        TopicEntity topicEntity = new TopicEntity();
        topicEntity.setPublic(true);
        topicEntity.setTitle("");
        _topicId = _topicDao.create(topicEntity);
    }

    @After
    public void teardown() {
        _userDao.delete(_userId);
        _topicDao.delete(_topicId);
    }

    @Test
    public void createAndListMessages() {
        assertTrue(_messageDao.list(_topicId).isEmpty());

        MessageEntity entity = new MessageEntity();
        entity.setText("My Message");
        entity.setOwnerId(_userId);
        entity.setTopicId(_topicId);

        Long id = _messageDao.create(entity);
        assertNotNull(id);
        entity = _messageDao.get(id);

        assertNotNull(entity);
        assertEquals("My Message", entity.getText());
        assertEquals(_userId, entity.getOwnerId());
        assertEquals(_topicId, entity.getTopicId());
        assertNotNull(entity.getDateAndTime());

        assertEquals(1, _messageDao.list(_topicId).size());
    }
}
