package com.line.store.config;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtToken implements Serializable {
	
	private static final long serialVersionUID = 3779837182611265215L;

	public static final long JWT_TOKEN_VALIDITY = 8 * 60 * 60 * 1000;

	@Value("${jwt.secret}")
	private String secret;

	public String getUser(String token) {

		return getRequest(token, Claims::getSubject);

	}

	// retrieve expiration date from jwt token
	public Date getExpiration(String token) {

		return getRequest(token, Claims::getExpiration);

	}

	public <T> T getRequest(String token, Function<Claims, T> claimsResolver) {

		final Claims requests = getAllRequests(token);

		return claimsResolver.apply(requests);

	}

	// for retrieveing any information from token we will need the secret key
	private Claims getAllRequests(String token) {

		return Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();

	}

	// check if the token has expired
	private Boolean expiredToken(String token) {
		final Date expirationDate = getExpiration(token);
		return expirationDate.before(new Date());
	}

	// generate token for user
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> requests = new HashMap<>();

		return generate(requests, userDetails.getUsername());
	}

	// while creating the token -
	// 1. Define claims of the token, like Issuer, Expiration, Subject, and the ID
	// 2. Sign the JWT using the HS512 algorithm and secret key.
	// 3. According to JWS Compact
	// Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
	// compaction of the JWT to a URL-safe string
	private String generate(Map<String, Object> requests, String username) {

		return Jwts.builder().setClaims(requests).setSubject(username)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	// validate token
	public Boolean validateToken(String token, UserDetails userDetails) {

		final String username = this.getUser(token);

		return (username.equals(userDetails.getUsername()) && !this.expiredToken(token));

	}
}