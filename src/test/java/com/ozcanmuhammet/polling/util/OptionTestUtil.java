package com.ozcanmuhammet.polling.util;

import java.util.ArrayList;
import java.util.List;

import com.ozcanmuhammet.polling.dto.OptionDTO;
import com.ozcanmuhammet.polling.entity.Option;
import com.ozcanmuhammet.polling.entity.Question;

public class OptionTestUtil {

	public static Option getOption() {
		Option option = new Option();
		option.setId(1L);
		option.setOptionText("Option-1");
		Question question = new Question();
		question.setId(1L);
		option.setQuestion(question);
		return option;
	}
	
	public static List<Option> getOptionList() {
		List<Option> optionList = new ArrayList<Option>();
		optionList.add(getOption());
		return optionList;
	}
	

	public static OptionDTO getOptionDto() {
		OptionDTO option = new OptionDTO();
		option.setId(1L);
		option.setOptionText("Option-1");
		option.setQuestionId(1L);
		return option;
	}
	
	public static List<OptionDTO> getOptionDtoList() {
		List<OptionDTO> optionDtoList = new ArrayList<OptionDTO>();
		optionDtoList.add(getOptionDto());
		return optionDtoList;
	}
	
}
