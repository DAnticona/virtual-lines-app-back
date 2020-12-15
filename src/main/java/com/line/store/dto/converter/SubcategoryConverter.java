package com.line.store.dto.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.line.store.dto.SubcategoryDto;
import com.line.store.entity.Subcategory;

@Component
public class SubcategoryConverter extends AbstractConverter<Subcategory, SubcategoryDto> {
	
	@Autowired
	CategoryConverter categoryConverter;

	@Override
	public Subcategory fromDto(SubcategoryDto dto) {
		Subcategory subcategory = new Subcategory();
		
		subcategory.setName(dto.getName());
		subcategory.setSubcategoryId(dto.getSubcategoryId());
		
		if(dto.getCategory() != null) {
			subcategory.setCategory(categoryConverter.fromDto(dto.getCategory()));
		}
		
		return subcategory;
	}

	@Override
	public SubcategoryDto fromEntity(Subcategory entity) {
		SubcategoryDto subcategory = new SubcategoryDto();
		subcategory.setName(entity.getName());
		subcategory.setSubcategoryId(entity.getSubcategoryId());
		
		if(entity.getCategory() != null) {
			subcategory.setCategory(categoryConverter.fromEntity(entity.getCategory()));
		}
		
		return subcategory;
	}

//	@Override
//	public SubcategoryDto fromEntity(Subcategory entity, boolean value1, boolean value2) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
