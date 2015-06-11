package com.forum.webapp.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.forum.webapp.dao.IUserDao;
import com.forum.webapp.entities.UserEntity;
import com.forum.webapp.repos.UserRepository;
import com.google.common.collect.Lists;

@Transactional
@Component
public class UserDao implements IUserDao {

    private final static Logger LOG = Logger.getLogger(UserDao.class);

    @Autowired
    private UserRepository repository;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private DataSource dataSource;

    public Long create(final UserEntity entity) {
        final long count = (Long) entityManager.createNamedQuery("checkUniqueness")
                .setParameter("email", entity.getEmail()).getSingleResult();
        if (count > 0) {
            throw new DuplicateKeyException("Un utilisateur existe déjà pour cette adresse e-mail.");
        }
        repository.save(entity);
        return entity.getId();
    }

    public void delete(final Long id) {
        repository.delete(id);
    }

    public void update(final UserEntity entity) {
        repository.save(entity);
    }

    public UserEntity get(final Long id) {
        UserEntity result = repository.findOne(id);
        return result;
    }

    public UserEntity login(final String email, final String password) {
        final StringBuffer strBuf = new StringBuffer("select id from users");
        strBuf.append(" where email = '").append(email).append("'");
        strBuf.append(" and password = '").append(password).append("'");

        try {
            System.out.println(strBuf.toString());
            Statement statement = dataSource.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(strBuf.toString());

            Long id = null;
            if (resultSet.first()) {
                id = resultSet.getLong("id");
                if (null != id) {
                    return get(id);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<UserEntity> list() {
        return Lists.newLinkedList(repository.findAll());
    }

}
