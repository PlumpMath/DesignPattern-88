package com.forum.webapp.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.forum.webapp.dao.IUserDao;
import com.forum.webapp.entities.UserEntity;
import com.forum.webapp.services.IUserService;
import com.forum.webapp.web.models.User;

@Service("userService")
public class UserService implements IUserService {

    private IUserDao userDao;

    @Autowired
    public void setUserDao(final IUserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    public Long create(final User entity) {
        return userDao.create(entity.toEntity());
    }

    @Transactional
    public void delete(final Long id) {
        userDao.delete(id);
    }

    @Transactional
    public void update(final User entity) {
        userDao.update(entity.toEntity());
    }

    @Transactional
    public User get(final Long id) {
        UserEntity user = userDao.get(id);
        if (null != user) {
            return new User(user);
        }
        return null;
    }

    @Transactional
    public User login(String email, String password) {
        UserEntity user = userDao.login(email, password);
        if (null != user) {
            return new User(user);
        }
        return null;
    }

}
