package com.line.store.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.line.store.config.JwtToken;
import com.line.store.constant.ApiState;
import com.line.store.dao.UserDao;
import com.line.store.dto.ApiResponse;
import com.line.store.dto.AuthUser;
import com.line.store.dto.converter.AuthUserConverter;
import com.line.store.entity.User;
import com.line.store.exception.ApiException;

@Service("userDetailsService")
public class UserService implements UserDetailsService {
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	JwtToken jwtToken;
	
	@Autowired
	AuthUserConverter authUserConverter;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		User user = userDao.findByEmail(email);

		if (user == null) {
			throw new UsernameNotFoundException(email);
		}
		
		if("N".equals(user.getActiveFg())) {
			throw new DisabledException(email);
		}

		List<GrantedAuthority> roles = new ArrayList<>();

		roles.add(new SimpleGrantedAuthority(user.getRole().getName()));

		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), roles);
	}
	
	public ApiResponse login(String request) throws ApiException {
		
		AuthUser authUser;
		
		JsonNode root;
		
		String email = null;
		String password = null;
		
		try {
			root = new ObjectMapper().readTree(request);

			email = root.path("email").asText();
			password = root.path("password").asText();
			
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

		} catch (JsonProcessingException e) {
			throw new ApiException(ApiState.NO_APPLICATION_PROCESSED.getCode(), ApiState.NO_APPLICATION_PROCESSED.getMessage(), e.getMessage());
			
		} catch (DisabledException e) {
			throw new ApiException(ApiState.USER_DISABLED.getCode(), ApiState.USER_DISABLED.getMessage(), e.getMessage());
			
		} catch (BadCredentialsException e) {
			throw new ApiException(ApiState.LOGIN_FAILED.getCode(), ApiState.LOGIN_FAILED.getMessage(), e.getMessage());

		}
		
		final UserDetails userDetails = userDetailsService.loadUserByUsername(email);
		final String token = jwtToken.generateToken(userDetails);
		User user = userDao.findByEmail(email);
		
		authUser = new AuthUser();
		authUser = authUserConverter.fromEntity(user);
		
		if(authUser.getActiveFg().equalsIgnoreCase("N")) {
			throw new ApiException(ApiState.USER_DISABLED.getCode(), ApiState.USER_DISABLED.getMessage());
		}
		
		authUser.setToken(token);
		
		return ApiResponse.of(ApiState.SUCCESS.getCode(), ApiState.SUCCESS.getMessage(), authUser, null);
	}

}
