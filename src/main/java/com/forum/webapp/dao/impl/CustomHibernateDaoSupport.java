package com.forum.webapp.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public abstract class CustomHibernateDaoSupport {

    private transient SessionFactory _sessionFactory;

    private transient Session _session;

    @Autowired
    @Qualifier("sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this._sessionFactory = sessionFactory;
    }

    final protected SessionFactory getSessionFactory() {
        return _sessionFactory;
    }

    final protected Session getSession() {
        if (null == _session) {
            synchronized (this) {
                if (null == _session) {
                    _session = _sessionFactory.openSession();
                }
            }
        }
        return _session;
    }
}
