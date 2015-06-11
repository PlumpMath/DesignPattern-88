package com.forum.webapp.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.Collection;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.forum.webapp.entities.TopicEntity;
import com.forum.webapp.entities.UserEntity;
import com.forum.webapp.repos.ShareRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:test-applicationContext.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class TopicDaoTest {

	Long usersId[] = new Long[3];

	@Autowired
	protected ITopicDao topicDao;

	@Autowired
	protected IUserDao userDao;

	@Autowired
	protected ShareRepository shareRepository;

	@Before
	public void setup() {
		UserEntity userEntity = new UserEntity();
		userEntity.setFirstName("First");
		userEntity.setName("Creator");
		userEntity.setEmail("creator@titi.com");
		userEntity.setPassword("pwd");
		usersId[0] = userDao.create(userEntity);

		userEntity = new UserEntity();
		userEntity.setFirstName("First");
		userEntity.setName("AllowedReader");
		userEntity.setEmail("allowedreader@titi.com");
		userEntity.setPassword("pwd");
		usersId[1] = userDao.create(userEntity);

		userEntity = new UserEntity();
		userEntity.setFirstName("First");
		userEntity.setName("NotAllowedReader");
		userEntity.setEmail("notallowedreader@titi.com");
		userEntity.setPassword("pwd");
		usersId[2] = userDao.create(userEntity);
	}

	@After
	public void teardown() {
		for (Long userId : usersId) {
			userDao.delete(userId);
		}
	}

	@Test
	public void createNewPrivateTopic() {
		// Check the initial state. A default topic already exists.
		assertEquals(1, topicDao.list(usersId[0]).size());
		assertEquals(1, topicDao.list(usersId[1]).size());

		// Create a new private topic.
		TopicEntity topicEntity = new TopicEntity();
		topicEntity.setPublicTopic(false);
		topicEntity.setTitle("Title");
		topicEntity.setCreatorId(usersId[0]);
		Long topicId = topicDao.create(topicEntity);

		// The creator can see the new topic.
		List<TopicEntity> topics = topicDao.list(usersId[0]);
		assertThat(
		        topics,
		        Matchers.<Collection<TopicEntity>>allOf(Matchers.hasSize(2),
		                Matchers.<TopicEntity>hasItem(Matchers.hasProperty("id", Matchers.is(topicId)))));
		// The other users cannot.
		topics = topicDao.list(usersId[1]);
		assertThat(
		        topics,
		        Matchers.<Collection<TopicEntity>>allOf(Matchers.hasSize(1),
		                Matchers.not(Matchers.<TopicEntity>hasItem(Matchers.hasProperty("id", Matchers.is(topicId))))));
	}

	@Test
	public void createNewPublicTopic() {
		// TODO Create a new public topic and check if all the users can have it
		// in their list.
	}

	@Test
	public void sharePrivateTopic() {
		// TODO Create a new private topic, share it with the user[1] and check
		// if it can have it
		// in its list, but not the user[2].

		// Create a share on the topic.
		/*
		 * ShareEntity share = new ShareEntity(); share.setTopicId(topicId);
		 * share.setReaderId(usersId[1]); shareRepository.save(share);
		 */
	}
}
