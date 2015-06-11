package com.forum.webapp.services;

import com.forum.webapp.web.models.IModel;

public interface IService<T extends IModel> {

    Long create(final T entity);

    T get(final Long id);

}
