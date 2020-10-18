package com.ozcanmuhammet.polling.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.ozcanmuhammet.polling.dto.QuestionDTO;
import com.ozcanmuhammet.polling.entity.Poll;
import com.ozcanmuhammet.polling.mapper.OptionMapper;
import com.ozcanmuhammet.polling.mapper.QuestionMapper;
import com.ozcanmuhammet.polling.model.QuestionRequest;
import com.ozcanmuhammet.polling.model.QuestionUpdateRequest;
import com.ozcanmuhammet.polling.repository.AnswerRepository;
import com.ozcanmuhammet.polling.repository.PollRepository;
import com.ozcanmuhammet.polling.repository.QuestionRepository;
import com.ozcanmuhammet.polling.repository.UserRepository;
import com.ozcanmuhammet.polling.util.OptionTestUtil;
import com.ozcanmuhammet.polling.util.QuestionTestUtil;
import com.ozcanmuhammet.polling.util.UserTestUtil;

@RunWith(MockitoJUnitRunner.class)
public class QuestionServiceTest {

	@InjectMocks
	private QuestionServiceImpl questionService;
	
	@Mock
	private QuestionRepository questionRepository;
	
	@Mock
	private QuestionMapper questionMapper;
	
	@Mock
	private OptionMapper optionMapper;
	
	@Mock
	private PollRepository pollRepository;
	
	@Mock
	private UserRepository userRepository;
	
	@Mock
	private AnswerRepository answerRepository;
	
	@Test
	public void whenGetActiveQuestion_thenReturnQuestionList() {
		given(questionRepository.findByStatus("1")).willReturn(QuestionTestUtil.getQuestionList());
		given(questionMapper.toDTO(any())).willReturn(QuestionTestUtil.getQuestionDTO());
		List<QuestionDTO> questionDtoList = questionService.getActiveQuestions();
		assertFalse(questionDtoList.isEmpty());
	}
	
	@Test
	public void whenGetPendingQuestions_thenReturnPendinQuestionList() {
		given(questionRepository.findByStatus("0")).willReturn(QuestionTestUtil.getQuestionList());
		given(questionMapper.toDTO(any())).willReturn(QuestionTestUtil.getQuestionDTO());
		List<QuestionDTO> questionDtoList = questionService.getPendingQuestions();
		assertFalse(questionDtoList.isEmpty());
	}
	
	@Test
	public void givenValidInput_whenSaveQuestionByAdminUser_thenReturnSuccess() {
		QuestionRequest questionRequest = QuestionTestUtil.getQuestionRequest();
		given(pollRepository.findById(any())).willReturn(Optional.of(new Poll()));
		given(userRepository.findById(any())).willReturn(Optional.of(UserTestUtil.getAdminUser()));
		given(questionRepository.save(any())).willReturn(QuestionTestUtil.getQuestion());
		given(questionMapper.toDTO(any())).willReturn(QuestionTestUtil.getQuestionDTO());
		
		QuestionDTO questionDTO = questionService.saveQuestion(questionRequest);
		
		assertNotNull(questionDTO);
		verify(questionRepository, times(1)).save(any());
		
	}
	
	@Test
	public void givenValidInput_whenSaveQuestionByEndUser_thenReturnSuccess() {
		QuestionRequest questionRequest = QuestionTestUtil.getQuestionRequest();
		given(pollRepository.findById(any())).willReturn(Optional.of(new Poll()));
		given(userRepository.findById(any())).willReturn(Optional.of(UserTestUtil.getEndUser()));
		given(questionRepository.save(any())).willReturn(QuestionTestUtil.getQuestion());
		given(questionMapper.toDTO(any())).willReturn(QuestionTestUtil.getQuestionDTO());
		
		QuestionDTO questionDTO = questionService.saveQuestion(questionRequest);
		
		assertNotNull(questionDTO);
		verify(questionRepository, times(1)).save(any());
		
	}
	
	@Test
	public void givenValidInput_whenUpdateQuestion_thenReturnSuccess() {
		QuestionUpdateRequest request = QuestionTestUtil.getQuestionUpdateRequest();
		given(questionRepository.findById(any())).willReturn(Optional.of(QuestionTestUtil.getQuestion()));
		given(optionMapper.toEntity(any())).willReturn(OptionTestUtil.getOption());
		given(questionRepository.save(any())).willReturn(QuestionTestUtil.getQuestion());
		given(questionMapper.toDTO(any())).willReturn(QuestionTestUtil.getQuestionDTO());
	
		QuestionDTO questionDTO = questionService.updateQuestion(1L, request);
		
		assertNotNull(questionDTO);
		verify(questionRepository, times(1)).save(any());
	}
	
	@Test
	public void givenValidInput_whenGetQuestionsByPollId_thenReturnSuccess() {
		given(pollRepository.findById(any())).willReturn(Optional.of(new Poll()));
		given(questionRepository.findByPollAndStatus(any(), any())).willReturn(QuestionTestUtil.getQuestionList());
		given(questionMapper.toDTO(any())).willReturn(QuestionTestUtil.getQuestionDTO());
		
		List<QuestionDTO> questionDtoList = questionService.getQuestionsByPollId(1L);
		
		assertFalse(questionDtoList.isEmpty());
	}
}
