package com.ozcanmuhammet.polling.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "QUESTIONS")
@Getter
@Setter
public class Question {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "TEXT")
	private String text;

	@Column(name = "STATUS")
	private String status;

	@ManyToOne
	@JoinColumn(name = "POLL_ID")
	private Poll poll;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "question", cascade = CascadeType.ALL)
	private List<Option> options;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "question", cascade = CascadeType.ALL)
	private List<Answer> answers;

}
