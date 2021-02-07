package com.line.store.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.line.store.dto.ApiResponse;
import com.line.store.service.MenuService;

@RestController
@RequestMapping("/menus")
public class MenuRest {

	@Autowired
	MenuService menuService;

	@GetMapping
	public ResponseEntity<?> findAll() {

		ApiResponse response = menuService.findAll();

		return ResponseEntity.ok(response);

	}

}
