package com.forum.webapp.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:test-applicationContext.xml")
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = false)
public class TopicDaoTest {

    @Autowired
    @Qualifier("topicDao")
    protected ITopicDao _topicDao;

    @Test
    public void createNewTopic() {

    }

}
