package com.forum.webapp.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class CustomHibernateDaoSupport {

    private SessionFactory _sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this._sessionFactory = sessionFactory;
    }

    final protected SessionFactory getSessionFactory() {
        return _sessionFactory;
    }
}
