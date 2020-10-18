package com.ozcanmuhammet.polling.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ozcanmuhammet.polling.model.QuestionRequest;
import com.ozcanmuhammet.polling.model.QuestionUpdateRequest;
import com.ozcanmuhammet.polling.service.QuestionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

@Api("Question API")
@RestController
@AllArgsConstructor
public class QuestionController {

	@Autowired
	QuestionService questionService;

	@ApiOperation("Get Active Questions")
	@GetMapping(path = "/questions")
	public ResponseEntity<Object> getActiveQuestions() {
		return new ResponseEntity<Object>(questionService.getActiveQuestions(), HttpStatus.OK);
	}

	@ApiOperation("Save New Question")
	@PostMapping(path = "/questions")
	public ResponseEntity<Object> saveQuestion(@RequestBody QuestionRequest questionRequest) {
		return new ResponseEntity<Object>(questionService.saveQuestion(questionRequest), HttpStatus.OK);
	}

	@ApiOperation("Get Questions By Poll Id")
	@GetMapping("/questions/{pollId}")
	public ResponseEntity<Object> getPollQuestions(@PathVariable Long pollId) {
		return new ResponseEntity<Object>(questionService.getQuestionsByPollId(pollId), HttpStatus.OK);
	}

	@ApiOperation("Update a Question")
	@PutMapping("/questions/{questionId}")
	public ResponseEntity<Object> updateQuestion(@PathVariable Long questionId,
			@RequestBody QuestionUpdateRequest request) {
		return new ResponseEntity<Object>(questionService.updateQuestion(questionId, request), HttpStatus.OK);
	}

}
