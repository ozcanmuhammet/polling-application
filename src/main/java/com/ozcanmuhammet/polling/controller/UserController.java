package com.ozcanmuhammet.polling.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ozcanmuhammet.polling.model.LoginRequest;
import com.ozcanmuhammet.polling.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("User API")
@RestController
public class UserController {
	@Autowired
	UserService userService;

	@ApiOperation("Login the App")
	@PostMapping(path = "/login")
	public ResponseEntity<Object> login(@RequestBody LoginRequest request) {
		return new ResponseEntity<>(userService.login(request), HttpStatus.OK);
	}

}
