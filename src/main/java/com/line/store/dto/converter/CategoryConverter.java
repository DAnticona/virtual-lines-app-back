package com.line.store.dto.converter;

import org.springframework.stereotype.Component;

import com.line.store.dto.CategoryDto;
import com.line.store.entity.Category;

@Component
public class CategoryConverter extends AbstractConverter<Category, CategoryDto> {

	@Override
	public Category fromDto(CategoryDto dto) {
		Category category = new Category();

		category.setCategoryId(dto.getCategoryId());
		category.setName(dto.getName());
		category.setActiveFg(dto.getActiveFg());

		return category;
	}

	@Override
	public CategoryDto fromEntity(Category entity) {
		CategoryDto category = new CategoryDto();

		category.setCategoryId(entity.getCategoryId());
		category.setName(entity.getName());
		category.setActiveFg(entity.getActiveFg());

		return category;
	}

}
