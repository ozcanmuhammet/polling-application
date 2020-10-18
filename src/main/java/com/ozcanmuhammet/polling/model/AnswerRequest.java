package com.ozcanmuhammet.polling.model;

import java.util.List;

import com.ozcanmuhammet.polling.dto.AnswerDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerRequest {
	private Long userId;
	private List<AnswerDTO> answerList;
}
