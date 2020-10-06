package com.line.store.dto.converter;

import org.springframework.stereotype.Component;

import com.line.store.dto.SubcategoryDto;
import com.line.store.entity.Subcategory;
import com.line.store.entity.id.SubcategoryId;

@Component
public class SubcategoryConverter extends AbstractConverter<Subcategory, SubcategoryDto> {

	@Override
	public Subcategory fromDto(SubcategoryDto dto) {
		Subcategory subcategory = new Subcategory();
		subcategory.setName(dto.getName());
		
		if(dto.getCategoryId() != null && dto.getSubcategoryId() != null) {
			subcategory.setSubcategoryId(new SubcategoryId(dto.getCategoryId(), dto.getSubcategoryId()));
		}
		
		return subcategory;
	}

	@Override
	public SubcategoryDto fromEntity(Subcategory entity) {
		SubcategoryDto subcategory = new SubcategoryDto();
		subcategory.setName(entity.getName());
		
		if(entity.getSubcategoryId() != null) {
			subcategory.setCategoryId(entity.getSubcategoryId().getCategoryId());
			subcategory.setSubcategoryId(entity.getSubcategoryId().getSubcategoryId());
		}
		return subcategory;
	}

}
