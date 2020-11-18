package com.line.store.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.line.store.dto.ApiResponse;
import com.line.store.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryRest {
	
	@Autowired
	CategoryService categoryService;
	
	@GetMapping
	public ResponseEntity<?> findAllActives() {
		ApiResponse response;
		
		response = categoryService.findAllActives();
		
		return ResponseEntity.ok(response);
	}

}
