package com.line.store.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.line.store.constant.ApiState;
import com.line.store.dao.CategoryDao;
import com.line.store.dto.ApiResponse;
import com.line.store.dto.CategoryDto;
import com.line.store.dto.converter.CategoryConverter;
import com.line.store.entity.Category;
import com.line.store.exception.ApiException;

@Service
public class CategoryService {

	@Autowired
	CategoryDao categoryDao;
	@Autowired
	CategoryConverter categoryConverter;

	public ApiResponse findAll() {

		List<CategoryDto> categories = categoryConverter.fromEntity(categoryDao.findAll());

		return ApiResponse.of(ApiState.SUCCESS.getCode(), ApiState.SUCCESS.getMessage(), categories, categories.size());
	}

	public ApiResponse findAllActives() {

		List<CategoryDto> categories = categoryConverter.fromEntity(categoryDao.findByActiveFg("S"));

		return ApiResponse.of(ApiState.SUCCESS.getCode(), ApiState.SUCCESS.getMessage(), categories, categories.size());
	}
	
	public ApiResponse save(String request) throws ApiException {
		
		Category category;
		CategoryDto categoryDto;

		JsonNode root;

		Integer categoryId = null;
		String name = null;
		String activeFg = null;

		try {
			root = new ObjectMapper().readTree(request);

			categoryId = root.path("categoryId").asInt();
			name = root.path("name").asText();
			activeFg = root.path("activeFg").asText();

		} catch (JsonProcessingException e) {
			throw new ApiException(ApiState.NO_APPLICATION_PROCESSED.getCode(),
					ApiState.NO_APPLICATION_PROCESSED.getMessage(), e.getMessage());
		}

		try {

			category = new Category();
			
			category.setCategoryId(categoryId);
			category.setActiveFg(activeFg);
			category.setName(name);
			
			categoryDto = categoryConverter.fromEntity(categoryDao.save(category));

		} catch (Exception e) {
			throw new ApiException(ApiState.NO_APPLICATION_PROCESSED.getCode(),
					ApiState.NO_APPLICATION_PROCESSED.getMessage(), e.getMessage());
		}

		return ApiResponse.of(ApiState.SUCCESS.getCode(), ApiState.SUCCESS.getMessage(), categoryDto);
		
	}

}
