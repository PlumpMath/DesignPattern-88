package com.forum.webapp.dao.impl;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.forum.webapp.dao.IUserDao;
import com.forum.webapp.entities.UserEntity;

@Transactional
@Repository("userDao")
public class UserDao extends CustomHibernateDaoSupport implements IUserDao {

    public Long create(final UserEntity entity) {
        final long count = (Long) getSession().getNamedQuery("checkUniqueness").setString("email", entity.getEmail())
                .uniqueResult();
        if (count > 0) {
            throw new DuplicateKeyException("Un utilisateur existe déjà pour cette adresse e-mail.");
        }
        getSession().save(entity);
        return entity.getId();
    }

    @Transactional
    public void delete(final Long id) {
        getSession().getNamedQuery("deleteUser").setLong("id", id).executeUpdate();
    }

    @Transactional
    public void update(final UserEntity entity) {
        getSession().update(entity);
    }

    @Transactional
    public UserEntity get(final Long id) {
        UserEntity result = (UserEntity) getSession().get(UserEntity.class, id);
        result.setPassword(null);
        return result;
    }

    @Transactional
    public UserEntity login(final String email, final String password) {
        final Query query = getSession().getNamedQuery("login").setString("email", email)
                .setString("password", password);
        final Long id = (Long) query.uniqueResult();
        if (null != id) {
            return get(id);
        }
        return null;
    }

    @Transactional
    public List<UserEntity> list() {
        final Query query = getSession().getNamedQuery("listUserIds");
        @SuppressWarnings("unchecked")
        final List<Long> ids = (List<Long>) query.list();
        final List<UserEntity> result = new LinkedList<UserEntity>();
        for (Long id : ids) {
            result.add(get(id));
        }
        return result;
    }

}
