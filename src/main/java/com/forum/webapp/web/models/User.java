package com.forum.webapp.web.models;

import org.hibernate.validator.NotNull;
import org.hibernate.validator.Size;

import com.forum.webapp.entities.UserEntity;

public class User implements IModel {

    private Long _id;
    private String _name;
    private String _firstName;
    private String _email;
    private String _password;
    private String _passwordConfirmation;

    public User() {
        super();
    }

    public User(final UserEntity entity) {
        _id = entity.getId();
        _name = entity.getName();
        _firstName = entity.getFirstName();
        _email = entity.getEmail();
    }

    public long getId() {
        return _id;
    }

    public void setId(final long id) {
        _id = id;
    }

    public String getName() {
        return _name;
    }

    public void setName(final String name) {
        _name = name;
    }

    public String getFirstName() {
        return _firstName;
    }

    public void setFirstName(final String firstName) {
        _firstName = firstName;
    }

    public String getEmail() {
        return _email;
    }

    @NotNull
    @Size(max = 100, min = 1)
    public void setEmail(final String email) {
        _email = email;
    }

    @Size(max = 100, min = 1)
    public void setPassword(final String password) {
        this._password = password;
    }

    public String getPasswordConfirmation() {
        return _passwordConfirmation;
    }

    public void setPasswordConfirmation(final String passwordConfirmation) {
        this._passwordConfirmation = passwordConfirmation;
    }

    public UserEntity toEntity() {
        UserEntity result = new UserEntity();
        result.setId(_id);
        result.setName(_name);
        result.setFirstName(_firstName);
        result.setEmail(_email);
        result.setPassword(_password);

        return result;
    }
}
