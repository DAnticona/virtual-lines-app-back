package com.line.store.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.line.store.dto.ApiResponse;
import com.line.store.service.RoleService;

@RestController
@RequestMapping("/role")
public class RoleRest {
	
	@Autowired
	RoleService roleService;
	
	@GetMapping("/store")
	public ResponseEntity<?> findAllStoreRoles() {
		
		ApiResponse response = roleService.findAllStoreRoles();

		return ResponseEntity.ok(response);
	}

}
