package com.line.store.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.line.store.dto.ApiResponse;
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

		ApiResponse response = storeService.FindAllActives();

		return ResponseEntity.ok(response);
	}

}
