package com.line.store.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.line.store.constant.ApiState;
import com.line.store.dao.CategoryDao;
import com.line.store.dto.ApiResponse;
import com.line.store.dto.CategoryDto;
import com.line.store.dto.converter.CategoryConverter;


@Service
public class CategoryService {
	
	@Autowired
	CategoryDao categoryDao;
	@Autowired
	CategoryConverter categoryConverter;
	
	public ApiResponse findAllActives() {
		
		List<CategoryDto> categories = categoryConverter.fromEntity(categoryDao.findByActiveFg("S"));
		
		return ApiResponse.of(ApiState.SUCCESS.getCode(), ApiState.SUCCESS.getMessage(), categories, categories.size());
	}

}
