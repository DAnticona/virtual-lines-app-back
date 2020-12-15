package com.line.store.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.line.store.dto.ApiResponse;
import com.line.store.dto.ErrorResponse;
import com.line.store.exception.ApiException;
import com.line.store.service.StoreService;
import com.line.store.service.UserService;

@RestController
@RequestMapping("/store")
public class StoreRest {
	@Autowired
	StoreService storeService;
	@Autowired
	UserService userService;

	@GetMapping
	public ResponseEntity<?> findAllActives() {

		ApiResponse response = storeService.findAllActives();

		return ResponseEntity.ok(response);
	}

	@GetMapping("{id}")
	public ResponseEntity<?> findById(@PathVariable String id) {

		ApiResponse response = storeService.findById(id);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/{id}/users")
	public ResponseEntity<?> findUserByStoreId(@PathVariable String id) {

		ApiResponse response;
		try {
			response = userService.findByStoreId(id);
		} catch (ApiException e) {
			return new ResponseEntity<>(ErrorResponse.of(e.getCode(), e.getMessage(), e.getDetailMessage()),
					HttpStatus.PRECONDITION_FAILED);
		}

		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<?> update(@RequestBody String request) {

		ApiResponse response;

		try {
			response = storeService.update(request);

		} catch (ApiException e) {
			return new ResponseEntity<>(ErrorResponse.of(e.getCode(), e.getMessage(), e.getDetailMessage()),
					HttpStatus.PRECONDITION_FAILED);

		}

		return ResponseEntity.ok(response);
	}

}
