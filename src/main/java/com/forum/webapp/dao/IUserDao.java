package com.forum.webapp.dao;

import java.util.List;

import com.forum.webapp.entities.UserEntity;

public interface IUserDao extends IDao<UserEntity> {

    Long create(UserEntity entity);

    void delete(Long id);

    void update(UserEntity entity);

    UserEntity get(Long id);

    List<UserEntity> list();

    UserEntity login(String email, String password);

}
