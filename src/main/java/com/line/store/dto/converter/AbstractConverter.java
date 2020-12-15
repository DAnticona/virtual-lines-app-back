package com.line.store.dto.converter;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractConverter<E, D> {

	public abstract E fromDto(D dto);

	public abstract D fromEntity(E entity);
	
//	public abstract D fromEntity(E entity, boolean value1, boolean value2);

	public List<E> fromDto(List<D> dtos) {
		if (dtos == null) {
			return null;
		}

		return dtos.stream().map(dto -> fromDto(dto)).collect(Collectors.toList());
	}

	public List<D> fromEntity(List<E> entities) {
		if (entities == null) {
			return null;
		}

		return entities.stream().map(entity -> fromEntity(entity)).collect(Collectors.toList());
	}
	
//	public List<D> fromEntity(List<E> entities, boolean value1, boolean value2) {
//		if (entities == null) {
//			return null;
//		}
//
//		return entities.stream().map(entity -> fromEntity(entity, value1, value2)).collect(Collectors.toList());
//	}

}
