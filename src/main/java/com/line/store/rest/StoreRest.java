package com.line.store.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
	public ResponseEntity<?> findAll() {

		ApiResponse response = storeService.findAll();

		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/slice/{page}")
	public ResponseEntity<?> findAll(@PathVariable int page) {

		ApiResponse response = storeService.findAll(page);

		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/category/{categoryId}")
	public ResponseEntity<?> findByCategory(@PathVariable Integer categoryId) {
		ApiResponse response;
		try {
			response = storeService.findByCategory(categoryId);
		} catch (ApiException e) {
			return new ResponseEntity<>(ErrorResponse.of(e.getCode(), e.getMessage(), e.getDetailMessage()),
					HttpStatus.PRECONDITION_FAILED);
		}
		return ResponseEntity.ok(response);
	}

	@GetMapping("/search/{term}")
	public ResponseEntity<?> searchByPublicName(@PathVariable String term) {

		ApiResponse response = storeService.searchByPublicName(term);

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

	@PutMapping
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

	@PostMapping("/avatar")
	public ResponseEntity<?> changeAvatar(@RequestParam("storeId") String storeId,
			@RequestParam("file") MultipartFile multipartFile) {

		ApiResponse response;

		try {

			response = storeService.changeAvatar(storeId, multipartFile);

		} catch (ApiException e) {
			return new ResponseEntity<>(ErrorResponse.of(e.getCode(), e.getMessage(), e.getDetailMessage()),
					HttpStatus.PRECONDITION_FAILED);

		}

		return ResponseEntity.ok(response);
	}

	@PostMapping("/image")
	public ResponseEntity<?> changeImage(@RequestParam("storeId") String storeId,
			@RequestParam("file") MultipartFile multipartFile) {

		ApiResponse response;

		try {

			response = storeService.changeImage(storeId, multipartFile);

		} catch (ApiException e) {
			return new ResponseEntity<>(ErrorResponse.of(e.getCode(), e.getMessage(), e.getDetailMessage()),
					HttpStatus.PRECONDITION_FAILED);

		}

		return ResponseEntity.ok(response);
	}

}
