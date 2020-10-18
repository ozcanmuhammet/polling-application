package com.ozcanmuhammet.polling.service;

import java.util.List;

import com.ozcanmuhammet.polling.dto.AnswerDTO;
import com.ozcanmuhammet.polling.dto.StatisticQuestionDTO;

public interface AnswerService {

	public void saveAnswerList(List<AnswerDTO> answerDtoList, Long userId);

	public List<StatisticQuestionDTO> getStatistics();

}
