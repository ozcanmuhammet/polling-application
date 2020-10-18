package com.ozcanmuhammet.polling.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.ozcanmuhammet.polling.common.enums.Exception;
import com.ozcanmuhammet.polling.common.util.JwtUtil;
import com.ozcanmuhammet.polling.entity.Role;
import com.ozcanmuhammet.polling.entity.User;
import com.ozcanmuhammet.polling.model.LoginRequest;
import com.ozcanmuhammet.polling.model.LoginResponse;
import com.ozcanmuhammet.polling.model.TokenRequest;
import com.ozcanmuhammet.polling.repository.RoleRepository;
import com.ozcanmuhammet.polling.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public LoginResponse login(LoginRequest request) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));

		User user = userRepository.findByUserName(request.getUserName());
		TokenRequest tokenRequest = new TokenRequest();
		tokenRequest.setUserId(user.getId());
		tokenRequest.setUserName(user.getUserName());

		Role role = roleRepository.findById(user.getRoleId()).orElseThrow(Exception.ROLE_NOT_FOUND::exception);
		List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(role.getName()));
		tokenRequest.setAuthorities(authorities);

		String token = jwtUtil.generateToken(tokenRequest);
		return new LoginResponse(token);
	}

}
