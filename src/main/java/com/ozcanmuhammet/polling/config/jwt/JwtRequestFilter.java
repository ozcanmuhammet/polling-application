package com.ozcanmuhammet.polling.config.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ozcanmuhammet.polling.common.util.JwtUtil;
import com.ozcanmuhammet.polling.service.JwtUserDetailsService;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;

	@Autowired
	private JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		final String requestTokenHeader = request.getHeader("Authorization");

		String userName = null;
		String token = null;

		// JWT Token is in the form "Bearer token". Remove Bearer word and get only the
		// token
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer")) {
			token = requestTokenHeader.substring(7);
			try {
				userName = jwtUtil.getUserNameFromToken(token);
			} catch (IllegalArgumentException e) {
				System.out.println("Unable to get JWT Token");
			}
		} else {
			logger.warn("JWT Token does not begin with Bearer String");
		}

		// Once we get the token, validate it
		if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(userName);

			// if token is valid configure Spring Security to manually set authentication
			if (jwtUtil.validateToken(token, userDetails)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				// After setting the authentication in the context, we specify that the current
				// user is authenticated
				// So it passes the Spring Security Configurations successfully.
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		filterChain.doFilter(request, response);
	}
}
