package com.ozcanmuhammet.polling.util;

import java.util.ArrayList;
import java.util.List;

import com.ozcanmuhammet.polling.common.enums.QuestionStatus;
import com.ozcanmuhammet.polling.dto.QuestionDTO;
import com.ozcanmuhammet.polling.entity.Poll;
import com.ozcanmuhammet.polling.entity.Question;
import com.ozcanmuhammet.polling.model.QuestionRequest;
import com.ozcanmuhammet.polling.model.QuestionUpdateRequest;

public class QuestionTestUtil {
	
	public static QuestionDTO getQuestionDTO() {
		QuestionDTO questionDTO = new QuestionDTO();
		questionDTO.setId(1L);
		questionDTO.setStatus(QuestionStatus.ACTIVE.getValue());
		questionDTO.setText("Question-1");
		questionDTO.setOptionDtoList(null);
		return questionDTO;
	}
	
	public static List<QuestionDTO> getQuestionDtoList() {
		List<QuestionDTO> questionDtoList = new ArrayList<QuestionDTO>();
		questionDtoList.add(getQuestionDTO());
		return questionDtoList;
	}
	
	public static QuestionRequest getQuestionRequest() {
		QuestionRequest questionRequest = new QuestionRequest();
		questionRequest.setUserId(1L);
		questionRequest.setPollId(1L);
		questionRequest.setQuestionText("Question-1");
		List<String> options = new ArrayList<String>();
		options.add("Option1");
		questionRequest.setOptionList(options);
		return questionRequest;
	}
	
	public static QuestionUpdateRequest getQuestionUpdateRequest() {
		QuestionUpdateRequest request = new QuestionUpdateRequest();
		request.setUserId(1L);
		request.setQuestionId(1L);
		request.setQuestionText("Question-1");
		request.setOptionList(OptionTestUtil.getOptionDtoList());
		return request;
	}
	
	public static Question getQuestion() {
		Question question = new Question();
		question.setId(1L);
		question.setStatus(QuestionStatus.ACTIVE.getValue());
		question.setText("Question-1");
		question.setPoll(new Poll());
		question.setAnswers(new ArrayList<>());
		question.setOptions(OptionTestUtil.getOptionList());
		return question;
	}
	
	public static List<Question> getQuestionList() {
		List<Question> questionList = new ArrayList<>();
		questionList.add(getQuestion());
		return questionList;
	}
		

}
