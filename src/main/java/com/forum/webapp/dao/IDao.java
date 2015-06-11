package com.forum.webapp.dao;

import com.forum.webapp.entities.IEntity;

public interface IDao<T extends IEntity> {

    Long create(final T entity);

    T get(final Long id);

}
