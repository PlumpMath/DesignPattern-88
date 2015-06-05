package com.forum.webapp.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.forum.webapp.entities.ShareEntity;

@Repository
public interface ShareRepository extends CrudRepository<ShareEntity, Long> {

}
