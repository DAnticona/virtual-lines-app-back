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
import com.line.store.service.SlotService;

@RestController
@RequestMapping("/slot")
public class SlotRest {

	@Autowired
	SlotService slotService;

	@GetMapping("/line/{id}")
	public ResponseEntity<?> findActivesByLine(@PathVariable String id) {

		ApiResponse response;

		try {
			response = slotService.findActivesByLineId(id);

		} catch (ApiException e) {
			return new ResponseEntity<>(ErrorResponse.of(e.getCode(), e.getMessage(), e.getDetailMessage()),
					HttpStatus.PRECONDITION_FAILED);

		}

		return ResponseEntity.ok(response);
	}

	@GetMapping("/line/{lineId}/user/{userId}")
	public ResponseEntity<?> findActiveByLineIdUserId(@PathVariable String lineId, @PathVariable String userId) {

		ApiResponse response;

		try {
			response = slotService.findActiveByLineIdUserId(lineId, userId);

		} catch (ApiException e) {
			return new ResponseEntity<>(ErrorResponse.of(e.getCode(), e.getMessage(), e.getDetailMessage()),
					HttpStatus.PRECONDITION_FAILED);

		}

		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<?> save(@RequestBody String request) {

		ApiResponse response;

		try {
			response = slotService.save(request);

		} catch (ApiException e) {
			return new ResponseEntity<>(ErrorResponse.of(e.getCode(), e.getMessage(), e.getDetailMessage()),
					HttpStatus.PRECONDITION_FAILED);

		}

		return ResponseEntity.ok(response);
	}

}
