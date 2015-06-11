package com.forum.webapp.services;

import com.forum.webapp.web.models.User;

public interface IUserService extends IService<User> {

    Long create(User entity);

    void delete(Long id);

    void update(User entity);

    User get(Long id);

    User login(String email, String password);

}
