package com.ozcanmuhammet.polling.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OptionDTO {
	private Long id;
	private Long questionId;
	private String optionText;
}
