package com.ozcanmuhammet.polling.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ozcanmuhammet.polling.model.AnswerRequest;
import com.ozcanmuhammet.polling.service.AnswerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("Answer API")
@RestController
public class AnswerController {

	@Autowired
	AnswerService answerService;

	@ApiOperation("Save the Poll Answers")
	@PostMapping(path = "/answers")
	public ResponseEntity<Object> saveAnswerList(@RequestBody AnswerRequest answerRequest) {
		answerService.saveAnswerList(answerRequest.getAnswerList(), answerRequest.getUserId());
		return new ResponseEntity<Object>(null, HttpStatus.OK);
	}

}
