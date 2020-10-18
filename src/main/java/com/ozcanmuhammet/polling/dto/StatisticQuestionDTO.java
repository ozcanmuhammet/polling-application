package com.ozcanmuhammet.polling.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatisticQuestionDTO {
	private Long questionId;
	private String questionText;
	private List<StatisticOptionDTO> statisticOptionDtoList;

}
