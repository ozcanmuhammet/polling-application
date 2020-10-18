package com.ozcanmuhammet.polling.service;

import com.ozcanmuhammet.polling.model.LoginRequest;
import com.ozcanmuhammet.polling.model.LoginResponse;

public interface UserService {

	public LoginResponse login(LoginRequest request);
}
