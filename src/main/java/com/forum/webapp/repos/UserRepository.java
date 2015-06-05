package com.forum.webapp.repos;

import org.springframework.data.repository.CrudRepository;

import com.forum.webapp.entities.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

}
