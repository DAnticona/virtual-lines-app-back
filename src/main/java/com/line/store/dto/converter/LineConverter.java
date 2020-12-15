package com.line.store.dto.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.line.store.dto.LineDto;
import com.line.store.entity.Line;
import com.line.store.util.Utils;

@Component
public class LineConverter extends AbstractConverter<Line, LineDto> {

	@Autowired
	StoreConverter storeConverter;
	@Autowired
	SlotConverter slotConverter;
	@Autowired
	UserConverter userConverter;
	@Autowired
	Utils UTILS;
	
	@Override
	public Line fromDto(LineDto dto) {
		Line line = new Line();
		
		line.setLineId(dto.getLineId());
		line.setName(dto.getName());
		line.setActiveFg(dto.getActiveFg());
		
		if(dto.getStore() != null) {
			line.setStore(storeConverter.fromDto(dto.getStore()));
		}
		
//		if(dto.getSlots() != null) {
//			line.setSlots(slotConverter.fromDto(dto.getSlots()));
//		}
		
		return line;
	}

	@Override
	public LineDto fromEntity(Line entity) {
		LineDto line = new LineDto();
		
		line.setLineId(entity.getLineId());
		line.setName(entity.getName());
		line.setActiveFg(entity.getActiveFg());
		
		if(entity.getStore() != null) {
			line.setStore(storeConverter.fromEntity(entity.getStore()));
		}
		
//		if(entity.getSlots() != null) {
//			line.setSlots(slotConverter.fromEntity(entity.getSlots(), false, true));
//		}
		
		return line;
	}

//	@Override
//	public LineDto fromEntity(Line entity, boolean value1, boolean value2) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
