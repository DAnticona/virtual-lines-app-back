package com.line.store.dto.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.line.store.dto.StoreDto;
import com.line.store.entity.Store;

@Component
public class StoreConverter extends AbstractConverter<Store, StoreDto> {

	@Autowired
	SubcategoryConverter subcategoryConverter;
	
	@Override
	public Store fromDto(StoreDto dto) {
		Store store = new Store();
		
		store.setStoreId(dto.getStoreId());
		store.setDescription(dto.getDescription());
		store.setActiveFg(dto.getActiveFg());
		store.setImage(dto.getImage());
		store.setLatitude(dto.getLatitude());
		store.setLongitude(dto.getLongitude());
		store.setPhone(dto.getPhone());
		store.setPublicName(dto.getPublicName());
		store.setWebsite(dto.getWebsite());
		
		if(dto.getSubcategory() != null) {
			store.setSubcategory(subcategoryConverter.fromDto(dto.getSubcategory()));
		}
		
		return store;
	}

	@Override
	public StoreDto fromEntity(Store entity) {
		StoreDto store = new StoreDto();
		
		store.setStoreId(entity.getStoreId());
		store.setDescription(entity.getDescription());
		store.setActiveFg(entity.getActiveFg());
		store.setImage(entity.getImage());
		store.setLatitude(entity.getLatitude());
		store.setLongitude(entity.getLongitude());
		store.setPhone(entity.getPhone());
		store.setPublicName(entity.getPublicName());
		store.setWebsite(entity.getWebsite());
		
		if(entity.getSubcategory() != null) {
			store.setSubcategory(subcategoryConverter.fromEntity(entity.getSubcategory()));
		}
		
		return store;
	}

}
