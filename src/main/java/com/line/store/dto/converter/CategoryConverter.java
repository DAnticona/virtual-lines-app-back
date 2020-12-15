package com.line.store.dto.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.line.store.dto.CategoryDto;
import com.line.store.entity.Category;

@Component
public class CategoryConverter extends AbstractConverter<Category, CategoryDto> {
	
	@Autowired
	SubcategoryConverter subcategoryConverter;

	@Override
	public Category fromDto(CategoryDto dto) {
		Category category = new Category();
		
		category.setCategoryId(dto.getCategoryId());
		category.setName(dto.getName());
		category.setActiveFg(dto.getActiveFg());
		
//		if(dto.getSubcategories() != null) {
//			subcategoryConverter.fromDto(dto.getSubcategories());
//		}
		return category;
	}

	@Override
	public CategoryDto fromEntity(Category entity) {
		CategoryDto category = new CategoryDto();
		
		category.setCategoryId(entity.getCategoryId());
		category.setName(entity.getName());
		category.setActiveFg(entity.getActiveFg());
		
//		if (entity.getSubcategories() != null) {
//			subcategoryConverter.fromEntity(entity.getSubcategories());
//		}
		
		return category;
	}

//	@Override
//	public CategoryDto fromEntity(Category entity, boolean value1, boolean value2) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	

}
