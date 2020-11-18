package com.line.store.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
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
import com.line.store.dao.RoleDao;
import com.line.store.dao.StoreDao;
import com.line.store.dao.UserDao;
import com.line.store.dto.ApiResponse;
import com.line.store.dto.AuthUser;
import com.line.store.dto.UserDto;
import com.line.store.dto.converter.AuthUserConverter;
import com.line.store.dto.converter.UserConverter;
import com.line.store.entity.Role;
import com.line.store.entity.Store;
import com.line.store.entity.User;
import com.line.store.exception.ApiException;
import com.line.store.util.CryptPassword;

@Service("userDetailsService")
public class UserService implements UserDetailsService {

	@Autowired
	UserDao userDao;
	@Autowired
	StoreDao storeDao;
	@Autowired
	RoleDao roleDao;
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	UserDetailsService userDetailsService;
	@Autowired
	JwtToken jwtToken;
	@Autowired
	AuthUserConverter authUserConverter;
	@Autowired
	UserConverter userConverter;
	@Autowired
	CryptPassword cryptPassword;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		User user = userDao.findByEmail(email);

		if (user == null) {
			throw new UsernameNotFoundException(email);
		}

		if ("N".equals(user.getActiveFg())) {
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
			throw new ApiException(ApiState.NO_APPLICATION_PROCESSED.getCode(),
					ApiState.NO_APPLICATION_PROCESSED.getMessage(), e.getMessage());

		} catch (DisabledException e) {
			throw new ApiException(ApiState.USER_DISABLED.getCode(), ApiState.USER_DISABLED.getMessage(),
					e.getMessage());

		} catch (BadCredentialsException e) {
			throw new ApiException(ApiState.LOGIN_FAILED.getCode(), ApiState.LOGIN_FAILED.getMessage(), e.getMessage());

		}

		final UserDetails userDetails = userDetailsService.loadUserByUsername(email);
		final String token = jwtToken.generateToken(userDetails);
		User user = userDao.findByEmail(email);

		authUser = new AuthUser();
		authUser = authUserConverter.fromEntity(user);

		if (authUser.getActiveFg().equalsIgnoreCase("N")) {
			throw new ApiException(ApiState.USER_DISABLED.getCode(), ApiState.USER_DISABLED.getMessage());
		}

		authUser.setToken(token);

		return ApiResponse.of(ApiState.SUCCESS.getCode(), ApiState.SUCCESS.getMessage(), authUser, null);
	}

	public ApiResponse save(String request) throws ApiException {
		User newUser;
		UserDto user;

		JsonNode root;

		String userId = null;
		String storeId = null;
		Integer roleId = null;
		String storeFg = null;
		String name = null;
		String email = null;
		String password = null;
		String activeFg = null;
		String image = null;

		try {
			root = new ObjectMapper().readTree(request);

			userId = root.path("userId").asText();
			storeId = root.path("storeId").asText();
			roleId = root.path("roleId").asInt();
			storeFg = root.path("storeFg").asText();
			name = root.path("name").asText();
			email = root.path("email").asText();
			password = root.path("password").asText();
			activeFg = root.path("activeFg").asText();
			image = root.path("image").asText();

		} catch (JsonProcessingException e) {
			throw new ApiException(ApiState.NO_APPLICATION_PROCESSED.getCode(),
					ApiState.NO_APPLICATION_PROCESSED.getMessage(), e.getMessage());
		}

		Store store = null;
		if (storeFg.equals("S")) {
			store = storeDao.findById(storeId).orElseThrow(
					() -> new ApiException(ApiState.STORE_NOT_FOUND.getCode(), ApiState.STORE_NOT_FOUND.getMessage()));
		}

		Role role = roleDao.findById(roleId).orElseThrow(
				() -> new ApiException(ApiState.ROLE_NOT_FOUND.getCode(), ApiState.ROLE_NOT_FOUND.getMessage()));

		try {

			newUser = new User();

			if (StringUtils.isBlank(userId)) {
				userId = UUID.randomUUID().toString();
			}

			newUser.setUserId(userId);
			newUser.setStore(store);
			newUser.setRole(role);
			newUser.setStoreFg(storeFg);
			newUser.setName(name);
			newUser.setEmail(email);
			newUser.setPassword(cryptPassword.encode(password));
			newUser.setActiveFg(activeFg);
			if (StringUtils.isBlank(image)) {
				newUser.setImage(null);
			} else {
				newUser.setImage(image);
			}

			user = userConverter.fromEntity(userDao.save(newUser));

		} catch (Exception e) {
			throw new ApiException(ApiState.NO_APPLICATION_PROCESSED.getCode(),
					ApiState.NO_APPLICATION_PROCESSED.getMessage(), e.getMessage());
		}

		return ApiResponse.of(ApiState.SUCCESS.getCode(), ApiState.SUCCESS.getMessage(), user);
	}

}
