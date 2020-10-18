package com.ozcanmuhammet.polling.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.ozcanmuhammet.polling.common.enums.Exception;
import com.ozcanmuhammet.polling.entity.Role;
import com.ozcanmuhammet.polling.entity.User;
import com.ozcanmuhammet.polling.repository.RoleRepository;
import com.ozcanmuhammet.polling.repository.UserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) {
		User user = userRepository.findByUserName(userName);
		if (user == null) {
			Exception.USER_NOT_FOUND.raise();
		}

		Role role = roleRepository.findById(user.getRoleId()).orElseThrow(Exception.ROLE_NOT_FOUND::exception);

		List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(role.getName()));

		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				authorities);
	}
}
