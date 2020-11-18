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
import com.line.store.service.CategoryService;
import com.line.store.service.StoreService;
import com.line.store.service.SubcategoryService;
import com.line.store.service.UserService;

@RestController
@RequestMapping("/register")
public class Register {
	
	@Autowired
	UserService userService;
	@Autowired
	StoreService storeService;
	@Autowired
	CategoryService categoryService;
	@Autowired
	SubcategoryService subcategoryService;
	
	@PostMapping("/user/new-client-user")
	public ResponseEntity<?> createUserClient(@RequestBody String request) {

		ApiResponse response;
		
		try {

			response = userService.save(request);

		} catch (ApiException e) {
			return new ResponseEntity<>(ErrorResponse.of(e.getCode(), e.getMessage(), e.getDetailMessage()),
					HttpStatus.PRECONDITION_FAILED);

		}

		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/category")
	public ResponseEntity<?> findAllActives() {
		ApiResponse response;
		
		response = categoryService.findAllActives();
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/subcategory/{id}")
	public ResponseEntity<?> findByCategory(@PathVariable String id) {

		ApiResponse response;

		try {
			response = subcategoryService.findByCategory(id);

		} catch (ApiException e) {

			return new ResponseEntity<>(ErrorResponse.of(e.getCode(), e.getMessage(), e.getDetailMessage()),
					HttpStatus.PRECONDITION_FAILED);
		}

		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/store/new-store")
	public ResponseEntity<?> createStore(@RequestBody String request) {

		ApiResponse response;

		try {
			response = storeService.create(request);

		} catch (ApiException e) {
			return new ResponseEntity<>(ErrorResponse.of(e.getCode(), e.getMessage(), e.getDetailMessage()),
					HttpStatus.PRECONDITION_FAILED);

		}

		return ResponseEntity.ok(response);
	}

}
