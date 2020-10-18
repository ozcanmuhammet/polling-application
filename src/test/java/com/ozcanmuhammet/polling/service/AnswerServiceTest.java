package com.ozcanmuhammet.polling.service;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.ozcanmuhammet.polling.mapper.AnswerMapper;
import com.ozcanmuhammet.polling.repository.AnswerRepository;
import com.ozcanmuhammet.polling.repository.OptionRepository;
import com.ozcanmuhammet.polling.repository.QuestionRepository;
import com.ozcanmuhammet.polling.repository.UserRepository;
import com.ozcanmuhammet.polling.repository.specification.AnswerSpecification;
import com.ozcanmuhammet.polling.util.AnswerTestUtil;

@RunWith(MockitoJUnitRunner.class)
public class AnswerServiceTest {
	
	@InjectMocks
	private AnswerServiceImpl answerService;
	
	@Mock
	AnswerRepository answerRepository;

	@Mock
	QuestionRepository questionRepository;

	@Mock
	OptionRepository optionRepository;

	@Mock
	UserRepository userRepository;

	@Mock
	AnswerMapper answerMapper;

	@Mock
	AnswerSpecification answerSpecification;
	
	@Test
	public void whenGetStatistics_thenReturnSuccess() {
		given(answerSpecification.findAnswerCount()).willReturn(AnswerTestUtil.getResultList());
		
		answerService.getStatistics();
		
		verify(answerSpecification, times(1)).findAnswerCount();
	}

}
