package com.ozcanmuhammet.polling.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ozcanmuhammet.polling.common.enums.Exception;
import com.ozcanmuhammet.polling.dto.AnswerDTO;
import com.ozcanmuhammet.polling.dto.StatisticOptionDTO;
import com.ozcanmuhammet.polling.dto.StatisticQuestionDTO;
import com.ozcanmuhammet.polling.entity.Answer;
import com.ozcanmuhammet.polling.entity.Option;
import com.ozcanmuhammet.polling.entity.Question;
import com.ozcanmuhammet.polling.entity.User;
import com.ozcanmuhammet.polling.mapper.AnswerMapper;
import com.ozcanmuhammet.polling.repository.AnswerRepository;
import com.ozcanmuhammet.polling.repository.OptionRepository;
import com.ozcanmuhammet.polling.repository.QuestionRepository;
import com.ozcanmuhammet.polling.repository.UserRepository;
import com.ozcanmuhammet.polling.repository.specification.AnswerSpecification;

@Service
public class AnswerServiceImpl implements AnswerService {

	@Autowired
	AnswerRepository answerRepository;

	@Autowired
	QuestionRepository questionRepository;

	@Autowired
	OptionRepository optionRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	AnswerMapper answerMapper;

	@Autowired
	AnswerSpecification answerSpecification;

	@Override
	@Transactional
	public void saveAnswerList(List<AnswerDTO> answerDtoList, Long userId) {

		List<Answer> answerList = new ArrayList<Answer>();

		User user = userRepository.findById(userId).orElseThrow(Exception.USER_NOT_FOUND::exception);

		answerDtoList.forEach(answerDto -> {
			Answer answer = new Answer();
			Question question = questionRepository.findById(answerDto.getQuestionId())
					.orElseThrow(Exception.QUESTION_NOT_FOUND::exception);
			Option option = optionRepository.findById(answerDto.getOptionId())
					.orElseThrow(Exception.OPTION_NOT_FOUND::exception);
			answer.setQuestion(question);
			answer.setOption(option);
			answer.setUser(user);
			answerList.add(answer);
		});

		answerRepository.saveAll(answerList);
	}

	@Override
	public List<StatisticQuestionDTO> getStatistics() {

		List<StatisticQuestionDTO> statisticQuestionDtoList = new ArrayList<StatisticQuestionDTO>();
		
		Long tmpStatisticQuestionId=0L;
		int index=-1;
			
			for(Object[] result : answerSpecification.findAnswerCount()) {
				if((Long) result[0] != tmpStatisticQuestionId) {
					index++;
					tmpStatisticQuestionId = (Long) result[0];
					StatisticQuestionDTO statisticQuestionDto = new StatisticQuestionDTO();
					statisticQuestionDto.setQuestionId((Long) result[0]);
					statisticQuestionDto.setQuestionText((String) result[1]);
					
					List<StatisticOptionDTO> statisticOptionDTOList = new ArrayList<StatisticOptionDTO>();
					StatisticOptionDTO statisticOptionDto = new StatisticOptionDTO();
					
					statisticOptionDto.setOptionId((Long) result[2]);
					statisticOptionDto.setOptionText((String) result[3]);
					statisticOptionDto.setAnswerCount((Long) result[4]);
					statisticOptionDTOList.add(statisticOptionDto);	
					
					statisticQuestionDto.setStatisticOptionDtoList(statisticOptionDTOList);
					statisticQuestionDtoList.add(statisticQuestionDto);
				}
				else {
					StatisticOptionDTO statisticOptionDto = new StatisticOptionDTO();
					statisticOptionDto.setOptionId((Long) result[2]);
					statisticOptionDto.setOptionText((String) result[3]);
					statisticOptionDto.setAnswerCount((Long) result[4]);
					statisticQuestionDtoList.get(index).getStatisticOptionDtoList().add(statisticOptionDto);				
				}
			}
			
		return statisticQuestionDtoList;
	}

}
