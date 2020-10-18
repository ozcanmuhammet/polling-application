package com.ozcanmuhammet.polling.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ozcanmuhammet.polling.service.PollService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("Poll API")
@RestController
public class PollController {

	@Autowired
	PollService pollService;

	@ApiOperation("Get List of Polls")
	@GetMapping("/polls")
	public ResponseEntity<Object> getPolls() {
		return new ResponseEntity<Object>(pollService.getPolls(), HttpStatus.OK);
	}

}
