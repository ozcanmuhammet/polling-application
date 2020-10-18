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
@Table(name = "OPTIONS")
@Getter
@Setter
public class Option {

	// Option no olarak id kullanildi.
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "QUESTION_ID")
	private Question question;

	@Column(name = "OPTION_TEXT")
	private String optionText;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "option", cascade = CascadeType.ALL)
	private List<Answer> answers;

}
