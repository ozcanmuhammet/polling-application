package com.ozcanmuhammet.polling.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatisticOptionDTO {
	private Long optionId;
	private String optionText;
	private Long answerCount;
}
