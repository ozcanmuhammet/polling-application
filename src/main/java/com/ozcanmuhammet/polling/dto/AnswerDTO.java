package com.ozcanmuhammet.polling.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerDTO {

	private Long id;

	private Long userId;

	private Long questionId;

	private Long optionId;

}
