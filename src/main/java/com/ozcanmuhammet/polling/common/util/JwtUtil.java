package com.ozcanmuhammet.polling.common.util;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.ozcanmuhammet.polling.common.IConstants;
import com.ozcanmuhammet.polling.model.TokenRequest;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

	// Generate token for user
	public String generateToken(TokenRequest request) {
		String authorities = request.getAuthorities().stream().map(SimpleGrantedAuthority::getAuthority)
				.collect(Collectors.joining(","));

		Map<String, Object> claims = new HashMap<>();
		claims.put("USER_ID", request.getUserId());
		claims.put("USER_NAME", request.getUserName());
		claims.put("AUTHORITIES", authorities);

		final Date createdDate = new Date();
		final Date expirationDate = calculateExpirationDate(createdDate);

		return Jwts.builder().setClaims(claims).setSubject(request.getUserName()).setId(UUID.randomUUID().toString())
				.setIssuedAt(createdDate).setExpiration(expirationDate)
				.signWith(SignatureAlgorithm.HS512, "thisisthesecretkey").compact();
	}

	// Validate token
	public boolean validateToken(String token, UserDetails user) {
		final String email = getUserNameFromToken(token);
		return email.equals(user.getUsername()) && !isTokenExpired(token);
	}

	// Retrieve username from token
	public String getUserNameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	// Retrieve username from token
	public Long getUserIdFromToken(String token) {
		return getAllClaimsFromToken(token).get(IConstants.USER_ID, Long.class);
	}

	// Retrieve expiration date from token
	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	private Date calculateExpirationDate(Date createdDate) {
		int expirationInHours = 3;
		Instant expirationDate = createdDate.toInstant().plus(Duration.ofHours(expirationInHours));
		return Date.from(expirationDate);
	}

	// Check if the token has expired
	private Boolean isTokenExpired(String token) {
		final Date expirationDate = getExpirationDateFromToken(token);
		return expirationDate.before(new Date());
	}

	private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	// For retrieving any information from token, we will need the secret key
	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey("thisisthesecretkey").parseClaimsJws(token).getBody();
	}
}
