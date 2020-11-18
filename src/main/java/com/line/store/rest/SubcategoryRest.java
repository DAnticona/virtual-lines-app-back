package com.line.store.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.line.store.dto.ApiResponse;
import com.line.store.dto.ErrorResponse;
import com.line.store.exception.ApiException;
import com.line.store.service.SubcategoryService;

@RestController
@RequestMapping("/subcategory")
public class SubcategoryRest {

	@Autowired
	SubcategoryService subcategoryService;

	@GetMapping("{id}")
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

}
