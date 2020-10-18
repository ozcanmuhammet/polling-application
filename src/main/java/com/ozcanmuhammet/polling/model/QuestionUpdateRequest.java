package com.ozcanmuhammet.polling.model;

import java.util.List;

import com.ozcanmuhammet.polling.dto.OptionDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionUpdateRequest {
	private Long userId;
	private Long questionId;
	private String questionText;
	private List<OptionDTO> optionList;
}
