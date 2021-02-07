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
import org.springframework.web.bind.annotation.RestController;

import com.line.store.dto.ApiResponse;
import com.line.store.dto.ErrorResponse;
import com.line.store.exception.ApiException;
import com.line.store.service.ScheduleService;

@RestController
@RequestMapping("/schedule")
public class ScheduleRest {

	@Autowired
	ScheduleService scheduleService;

	@GetMapping("/store/{storeId}")
	public ResponseEntity<?> findAllByStoreId(@PathVariable String storeId) {

		ApiResponse response;

		try {
			response = scheduleService.findByStoreId(storeId);

		} catch (ApiException e) {

			return new ResponseEntity<>(ErrorResponse.of(e.getCode(), e.getMessage(), e.getDetailMessage()),
					HttpStatus.PRECONDITION_FAILED);
		}

		return ResponseEntity.ok(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable String id) {

		ApiResponse response;

		try {
			response = scheduleService.findById(id);

		} catch (ApiException e) {
			return new ResponseEntity<>(ErrorResponse.of(e.getCode(), e.getMessage(), e.getDetailMessage()),
					HttpStatus.PRECONDITION_FAILED);

		}

		return ResponseEntity.ok(response);
	}

	@GetMapping("/store/{storeId}/date/{date}")
	public ResponseEntity<?> findByDate(@PathVariable String storeId, @PathVariable String date) {

		ApiResponse response;

		try {
			response = scheduleService.findByStoreDate(storeId, Long.parseLong(date));

		} catch (ApiException e) {
			return new ResponseEntity<>(ErrorResponse.of(e.getCode(), e.getMessage(), e.getDetailMessage()),
					HttpStatus.PRECONDITION_FAILED);

		}

		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<?> createSchedule(@RequestBody String request) {

		ApiResponse response;

		try {
			response = scheduleService.save(request);

		} catch (ApiException e) {
			return new ResponseEntity<>(ErrorResponse.of(e.getCode(), e.getMessage(), e.getDetailMessage()),
					HttpStatus.PRECONDITION_FAILED);

		}

		return ResponseEntity.ok(response);
	}

	@PutMapping
	public ResponseEntity<?> updateSchedule(@RequestBody String request) {

		ApiResponse response;

		try {
			response = scheduleService.update(request);

		} catch (ApiException e) {
			return new ResponseEntity<>(ErrorResponse.of(e.getCode(), e.getMessage(), e.getDetailMessage()),
					HttpStatus.PRECONDITION_FAILED);

		}

		return ResponseEntity.ok(response);
	}

}
