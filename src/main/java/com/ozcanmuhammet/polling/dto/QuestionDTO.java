package com.ozcanmuhammet.polling.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionDTO {

	private Long id;
	private String text;
	private String status;
	private List<OptionDTO> optionDtoList;

}
