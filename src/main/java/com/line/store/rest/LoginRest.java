package com.line.store.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.line.store.dto.ApiResponse;
import com.line.store.dto.ErrorResponse;
import com.line.store.dto.converter.RoleConverter;
import com.line.store.dto.converter.UserConverter;
import com.line.store.exception.ApiException;
import com.line.store.service.UserService;

@RestController
@RequestMapping("/login")
public class LoginRest {

	@Autowired
	UserService userService;
	@Autowired
	UserConverter userConverter;
	@Autowired
	RoleConverter roleConverter;

	@PostMapping
	public ResponseEntity<?> login(@RequestBody String request) {

		ApiResponse response;
		
		try {
			
			response = userService.login(request);
			
		} catch (ApiException e) {
			return new ResponseEntity<>(ErrorResponse.of(e.getCode(), e.getMessage(), e.getDetailMessage()), HttpStatus.PRECONDITION_FAILED);
			
		}
		
		return ResponseEntity.ok(response);

	}

}
