package com.ozcanmuhammet.polling.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionRequest {
	private Long userId;
	private Long pollId;
	private String questionText;
	private List<String> optionList;
}
