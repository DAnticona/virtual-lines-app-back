package com.line.store.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import org.springframework.web.multipart.MultipartFile;

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
import com.line.store.util.Utils;

@Service("userDetailsService")
public class UserService implements UserDetailsService {

	@Value("${avatar.path}")
	private String avatarPath;

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
	@Autowired
	Utils UTILS;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		User user = userDao.findByEmail(email).orElse(null);

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

	public ApiResponse findByEmail(String email) {

		User user = userDao.findByEmail(email).orElse(null);
		UserDto userDto = null;

		if (user != null) {
			userDto = userConverter.fromEntity(user);
		}

		return ApiResponse.of(ApiState.SUCCESS.getCode(), ApiState.SUCCESS.getMessage(), userDto, null);
	}

	public ApiResponse findByStoreId(String storeId) throws ApiException {

		Store store = storeDao.findById(storeId).orElseThrow(
				() -> new ApiException(ApiState.STORE_NOT_FOUND.getCode(), ApiState.STORE_NOT_FOUND.getMessage()));

		List<UserDto> users = userConverter.fromEntity(userDao.findByStore(store));

		return ApiResponse.of(ApiState.SUCCESS.getCode(), ApiState.SUCCESS.getMessage(), users, users.size());
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

		User user = userDao.findByEmail(email).orElseThrow(
				() -> new ApiException(ApiState.USER_NOT_FOUND.getCode(), ApiState.USER_NOT_FOUND.getMessage()));

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

			if (StringUtils.isBlank(userId)) {
				userId = UUID.randomUUID().toString();
				newUser = new User();
				newUser.setPassword(cryptPassword.encode(password));
			} else {
				newUser = userDao.findById(userId).orElseThrow(() -> new ApiException(ApiState.USER_NOT_FOUND.getCode(),
						ApiState.USER_NOT_FOUND.getMessage()));
			}

			newUser.setUserId(userId);
			newUser.setStore(store);
			newUser.setRole(role);
			newUser.setStoreFg(storeFg);
			newUser.setName(name);
			newUser.setEmail(email);
			newUser.setActiveFg(activeFg);
			if (StringUtils.isBlank(image) || "null".equals(image)) {
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

	public ApiResponse changePassword(String request) throws ApiException {
		User newUser;
		UserDto user;

		JsonNode root;

		String userId = null;
		String password = null;

		try {
			root = new ObjectMapper().readTree(request);

			userId = root.path("userId").asText();
			password = root.path("password").asText();

		} catch (JsonProcessingException e) {
			throw new ApiException(ApiState.NO_APPLICATION_PROCESSED.getCode(),
					ApiState.NO_APPLICATION_PROCESSED.getMessage(), e.getMessage());
		}

		try {

			newUser = userDao.findById(userId).orElseThrow(
					() -> new ApiException(ApiState.USER_NOT_FOUND.getCode(), ApiState.USER_NOT_FOUND.getMessage()));

			newUser.setPassword(cryptPassword.encode(password));

			user = userConverter.fromEntity(userDao.save(newUser));

		} catch (Exception e) {
			throw new ApiException(ApiState.NO_APPLICATION_PROCESSED.getCode(),
					ApiState.NO_APPLICATION_PROCESSED.getMessage(), e.getMessage());
		}

		return ApiResponse.of(ApiState.SUCCESS.getCode(), ApiState.SUCCESS.getMessage(), user);
	}

	public ApiResponse changeAvatar(String userId, MultipartFile multipartFile) throws ApiException {
		User user;
		UserDto userDto;

		try {

			user = userDao.findById(userId).orElseThrow(
					() -> new ApiException(ApiState.USER_NOT_FOUND.getCode(), ApiState.USER_NOT_FOUND.getMessage()));

			if (!multipartFile.getContentType().equals("image/png")) {
				throw new ApiException(ApiState.IMAGE_INVALID.getCode(), ApiState.IMAGE_INVALID.getMessage(), null);
			}

			String filename = UUID.randomUUID().toString() + ".png";
						
			UTILS.multipartFileToFile(multipartFile, filename, avatarPath);
			
			if(user.getImage() != null) {
				String oldFilename = user.getImage();
				UTILS.deleteFile(oldFilename, avatarPath);	
			}

			user.setImage(filename);

			userDto = userConverter.fromEntity(userDao.save(user));

		} catch (Exception e) {
			throw new ApiException(ApiState.NO_APPLICATION_PROCESSED.getCode(),
					ApiState.NO_APPLICATION_PROCESSED.getMessage(), e.getMessage());
		}

		return ApiResponse.of(ApiState.SUCCESS.getCode(), ApiState.SUCCESS.getMessage(), userDto);
//		return ApiResponse.of(ApiState.SUCCESS.getCode(), ApiState.SUCCESS.getMessage(), null);
	}
}
