package com.forum.webapp.web.models;

import com.forum.webapp.entities.UserEntity;

public class User implements IModel {

    private Long id;
    private String name;
    private String firstName;
    private String email;
    private String password;
    private String passwordConfirmation;

    public User() {
        super();
    }

    public User(final UserEntity entity) {
        id = entity.getId();
        name = entity.getName();
        firstName = entity.getFirstName();
        email = entity.getEmail();
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(final String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }

    public UserEntity toEntity() {
        UserEntity result = new UserEntity();
        result.setId(id);
        result.setName(name);
        result.setFirstName(firstName);
        result.setEmail(email);
        result.setPassword(password);

        return result;
    }
}
