package com.ozcanmuhammet.polling.model;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class TokenRequest {
	private Long userId;
	private String userName;
	private List<SimpleGrantedAuthority> authorities;
}
