package com.ozcanmuhammet.polling.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ROLES")
@Getter
@Setter
public class Role {

	@Id
	@Column(name = "ID")
	private Long id;

	@Column(name = "NAME", unique = true)
	private String name;

}
