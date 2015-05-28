package com.forum.webapp.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.forum.webapp.entities.UserEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:test-applicationContext.xml")
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = false)
public class UserDaoTest {

    final private static long ID = 1234L;
    final private static String EMAIL = "tata.toto@titi.com";
    final private static String PASSWORD = "mypassword";
    private UserEntity _userEntity;
    private IUserDao _userDao;

    @Autowired
    @Qualifier("userDao")
    public void setUserDao(final IUserDao userDao) {
        _userDao = userDao;
    }
    
    @Before
    public void setup() throws Exception{
        clean();
        
        _userEntity = new UserEntity();
        _userEntity.setId(ID);
        _userEntity.setFirstName("Tata");
        _userEntity.setName("Toto");
        _userEntity.setEmail(EMAIL);
        _userEntity.setPassword(PASSWORD);
    }

    @After
    public void clean() throws Exception {
        for (UserEntity user : _userDao.list()) {
            _userDao.delete(user.getId());
        }
    }

    @Test
    public void createNewUser() {
        Long id = _userDao.create(_userEntity);
        assertNotNull(id);
        UserEntity entity = _userDao.get(id);
        assertNotNull(entity);
        assertNull(entity.getPassword());
    }

    @Test
    public void login() {
        Long id = _userDao.create(_userEntity);

        UserEntity entity = _userDao.login(EMAIL, PASSWORD);
        assertNotNull(entity);
        assertEquals(id, entity.getId());
    }

    @Test(expected = Exception.class)
    public void insertUserTwice() {
        _userDao.create(_userEntity);

        _userDao.create(_userEntity);
    }
}
