package com.ozcanmuhammet.polling.service;

import java.util.List;

import com.ozcanmuhammet.polling.dto.QuestionDTO;
import com.ozcanmuhammet.polling.model.QuestionRequest;
import com.ozcanmuhammet.polling.model.QuestionUpdateRequest;

public interface QuestionService {

	public List<QuestionDTO> getActiveQuestions();

	public List<QuestionDTO> getPendingQuestions();

	public QuestionDTO saveQuestion(QuestionRequest questionRequest);

	public List<QuestionDTO> getQuestionsByPollId(Long pollId);

	public QuestionDTO updateQuestion(Long questionId, QuestionUpdateRequest request);

}
