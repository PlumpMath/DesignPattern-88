package com.forum.webapp.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity
@Table(name = "users")
@NamedQueries(value = {
        @NamedQuery(name = "listUserIds", query = "select id from UserEntity"),
        @NamedQuery(name = "checkUniqueness", query = "select count(*) from UserEntity where email = :email"),
		@NamedQuery(name = "login", query = "select id from UserEntity where email = :email and password = :password"),
		@NamedQuery(name = "deleteUser", query = "delete from UserEntity where id = :id") })
public class UserEntity implements IEntity {

	private Long _id;

	private String _name;

	private String _firstName;

	private String _email;

	private String _password;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return _id;
	}

	public void setId(final Long id) {
		_id = id;
	}

	@Column(name = "name", length = 100, nullable = false)
	public String getName() {
		return _name;
	}

	public void setName(final String name) {
		_name = name;
	}

	@Column(name = "firstName", length = 100, nullable = false)
	public String getFirstName() {
		return _firstName;
	}

	public void setFirstName(final String firstName) {
		_firstName = firstName;
	}

	@Column(name = "email", length = 100, nullable = false, unique = true)
	public String getEmail() {
		return _email;
	}

	public void setEmail(final String email) {
		_email = email;
	}

	@Column(name = "password", length = 100, nullable = false)
	public String getPassword() {
		return _password;
	}

	public void setPassword(final String password) {
		this._password = password;
	}

}
