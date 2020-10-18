package com.ozcanmuhammet.polling.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ozcanmuhammet.polling.dto.QuestionDTO;
import com.ozcanmuhammet.polling.dto.StatisticQuestionDTO;
import com.ozcanmuhammet.polling.service.AnswerService;
import com.ozcanmuhammet.polling.service.QuestionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

@Api("Admin API")
@RestController
@AllArgsConstructor
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	QuestionService questionService;
	
	@Autowired
	AnswerService answerService;

	@ApiOperation("Get Pending Question List For Approve")
	@GetMapping(path = "/questions/pending")
	public List<QuestionDTO> getPendingQuestions() {
		return questionService.getPendingQuestions();
	}
	
	@ApiOperation("Get Answer Count Statistic")
	@GetMapping("/answers/statistics")
	public List<StatisticQuestionDTO> getStatistic() {
		return answerService.getStatistics();
	}
}
