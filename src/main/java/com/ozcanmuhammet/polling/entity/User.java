package com.ozcanmuhammet.polling.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "USERS")
@Getter
@Setter
public class User {

	@Id
	@Column(name = "ID")
	private Long id;

	@Column(name = "USER_NAME", unique = true)
	private String userName;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "ROLE_ID")
	private Long roleId;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
	private List<Answer> answers;

}
