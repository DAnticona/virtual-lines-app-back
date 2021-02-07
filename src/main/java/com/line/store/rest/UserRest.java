package com.line.store.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.line.store.dto.ApiResponse;
import com.line.store.dto.ErrorResponse;
import com.line.store.exception.ApiException;
import com.line.store.service.UserService;

@RestController
@RequestMapping("/user")
public class UserRest {

	@Autowired
	UserService userService;
	
	@GetMapping("/slice/{page}")
	public ResponseEntity<?> findAll(@PathVariable int page) {

		ApiResponse response;

		response = userService.findAll(page);

		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/search/{term}")
	public ResponseEntity<?> searchByNameOrEmail(@PathVariable String term) {

		ApiResponse response = userService.searchByNameOrEmail(term);

		return ResponseEntity.ok(response);
	}
	
	@GetMapping("{email}")
	public ResponseEntity<?> findByEmail(@PathVariable String email) {

		ApiResponse response;

		response = userService.findByEmail(email);

		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<?> createUser(@RequestBody String request) {

		ApiResponse response;
		
		try {

			response = userService.save(request);

		} catch (ApiException e) {
			return new ResponseEntity<>(ErrorResponse.of(e.getCode(), e.getMessage(), e.getDetailMessage()),
					HttpStatus.PRECONDITION_FAILED);

		}

		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/password")
	public ResponseEntity<?> changePassword(@RequestBody String request) {

		ApiResponse response;
		
		try {

			response = userService.changePassword(request);

		} catch (ApiException e) {
			return new ResponseEntity<>(ErrorResponse.of(e.getCode(), e.getMessage(), e.getDetailMessage()),
					HttpStatus.PRECONDITION_FAILED);

		}

		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/avatar")
	public ResponseEntity<?> changeAvatar(@RequestParam("userId") String userId, @RequestParam("file") MultipartFile multipartFile) {

		ApiResponse response;
		
		try {

			response = userService.changeAvatar(userId, multipartFile);

		} catch (ApiException e) {
			return new ResponseEntity<>(ErrorResponse.of(e.getCode(), e.getMessage(), e.getDetailMessage()),
					HttpStatus.PRECONDITION_FAILED);

		}

		return ResponseEntity.ok(response);
	}

}
