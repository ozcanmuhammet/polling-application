package com.ozcanmuhammet.polling.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.ozcanmuhammet.polling.common.exception.PollingExceptionHandler;
import com.ozcanmuhammet.polling.model.QuestionRequest;
import com.ozcanmuhammet.polling.model.QuestionUpdateRequest;
import com.ozcanmuhammet.polling.service.QuestionService;
import com.ozcanmuhammet.polling.util.QuestionTestUtil;

@RunWith(MockitoJUnitRunner.class)
public class QuestionControllerTest {
	
	private QuestionController questionController;
	private MockMvc mockMvc;
	private ObjectMapper objectMapper;
	
	private QuestionService questionService;
	
	@Before
	public void setUp() {
		questionService = mock(QuestionService.class);
		
		questionController = new QuestionController(questionService);
		objectMapper = new ObjectMapper();
		mockMvc = MockMvcBuilders.standaloneSetup(questionController)
				.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
				.setControllerAdvice(PollingExceptionHandler.class)
				.build();
	}
	
	private String getRequestJson(QuestionRequest request) throws Exception {
		objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = objectMapper.writer().withDefaultPrettyPrinter();
		return ow.writeValueAsString(request);
	}
	
	private String getRequestJson(QuestionUpdateRequest request) throws Exception {
		objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = objectMapper.writer().withDefaultPrettyPrinter();
		return ow.writeValueAsString(request);
	}
	
	@Test
	public void whenGetActiveQuestions_thenReturnQuestionList() throws Exception {
		given(questionService.getActiveQuestions()).willReturn(QuestionTestUtil.getQuestionDtoList());
	
		mockMvc.perform(get("/questions")
				.contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(status().isOk())
		.andReturn();
	}
	
	@Test
	public void givenValidInput_whenSaveQuestion_thenReturnSucess() throws Exception {
		QuestionRequest request = QuestionTestUtil.getQuestionRequest();
		String requestJson = getRequestJson(request);
		
		given(questionService.saveQuestion(any())).willReturn(QuestionTestUtil.getQuestionDTO());
		
		mockMvc.perform(post("/questions")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(requestJson))
		.andExpect(status().isOk())
		.andReturn();
	}
	
	@Test
	public void givenValidInput_whenGetPollQuestions_thenReturnSucess() throws Exception {
		given(questionService.getQuestionsByPollId(any())).willReturn(QuestionTestUtil.getQuestionDtoList());
		
		mockMvc.perform(get("/questions/1")
				.contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(status().isOk())
		.andReturn();
	}
	
	@Test
	public void givenValidInput_whenUpdateQuestion_thenReturnSucess() throws Exception {
		QuestionUpdateRequest request = QuestionTestUtil.getQuestionUpdateRequest();
		String requestJson = getRequestJson(request);
		
		given(questionService.updateQuestion(1L, request)).willReturn(QuestionTestUtil.getQuestionDTO());
		
		mockMvc.perform(put("/questions/1")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(requestJson))
		.andExpect(status().isOk())
		.andReturn();
	}

}
