package com.line.store.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.line.store.constant.ApiState;
import com.line.store.dao.CategoryDao;
import com.line.store.dao.SubcategoryDao;
import com.line.store.dto.ApiResponse;
import com.line.store.dto.SubcategoryDto;
import com.line.store.dto.converter.SubcategoryConverter;
import com.line.store.entity.Category;
import com.line.store.exception.ApiException;

@Service
public class SubcategoryService {

	@Autowired
	SubcategoryDao subcategoryDao;
	@Autowired
	CategoryDao categoryDao;
	@Autowired
	SubcategoryConverter subcategoryConverter;

	public ApiResponse findAllActives() {

		List<SubcategoryDto> subcategories = subcategoryConverter.fromEntity(subcategoryDao.findByActiveFg("S"));

		return ApiResponse.of(ApiState.SUCCESS.getCode(), ApiState.SUCCESS.getMessage(), subcategories,
				subcategories.size());
	}

	public ApiResponse findByCategory(String id) throws ApiException {

		Category category = categoryDao.findById(id)
				.orElseThrow(() -> new ApiException(ApiState.CATEGORY_NOT_FOUND.getCode(),
						ApiState.CATEGORY_NOT_FOUND.getMessage()));

		List<SubcategoryDto> subcategories = subcategoryConverter.fromEntity(subcategoryDao.findByCategory(category));

		return ApiResponse.of(ApiState.SUCCESS.getCode(), ApiState.SUCCESS.getMessage(), subcategories,
				subcategories.size());
	}

}
