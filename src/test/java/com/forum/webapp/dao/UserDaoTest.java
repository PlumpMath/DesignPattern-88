package com.forum.webapp.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.forum.webapp.entities.UserEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:test-applicationContext.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class UserDaoTest {

    final private static String EMAIL = "tata.toto@titi.com";
    final private static String PASSWORD = "mypassword";
    private UserEntity userEntity;
    private IUserDao userDao;

    @Autowired
    public void setUserDao(final IUserDao userDao) {
        this.userDao = userDao;
    }

    @Before
    public void setup() throws Exception {
        clean();

        userEntity = new UserEntity();
        userEntity.setFirstName("Tata");
        userEntity.setName("Toto");
        userEntity.setEmail(EMAIL);
        userEntity.setPassword(PASSWORD);
    }

    @After
    public void clean() throws Exception {
        for (UserEntity user : userDao.list()) {
            userDao.delete(user.getId());
        }
    }

    @Test
    public void createNewUser() {
        Long id = userDao.create(userEntity);
        assertNotNull(id);
        UserEntity entity = userDao.get(id);
        assertNotNull(entity);
    }

    @Test
    public void login() {
        Long id = userDao.create(userEntity);

        UserEntity entity = userDao.login(EMAIL, PASSWORD);
        assertNotNull(entity);
        assertEquals(id, entity.getId());
    }

    @Test
    @Ignore("Activate the test to see the security failure.")
    public void login_attack() {
        userDao.create(userEntity);

        String login = "' or 1 = 1 ; --";
        String password = "";

        UserEntity entity = userDao.login(login, password);
        assertNull(entity);
    }

    @Test(expected = Exception.class)
    public void insertUserTwice() {
        userDao.create(userEntity);

        userDao.create(userEntity);
    }
}
