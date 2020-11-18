package com.line.store.dto.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.line.store.dto.SlotDto;
import com.line.store.entity.Slot;

@Component
public class SlotConverter extends AbstractConverter<Slot, SlotDto>{

	@Autowired
	UserConverter userConverter;
	
	@Autowired
	LineConverter lineConverter;
	
	@Override
	public Slot fromDto(SlotDto dto) {
		Slot slot = new Slot();
		
		slot.setSlotId(dto.getSlotId());
		slot.setActiveFg(dto.getActiveFg());
		slot.setStartDate(dto.getStartDate());
		slot.setEndDate(dto.getEndDate());
		
		if(dto.getUser() != null) {
			slot.setUser(userConverter.fromDto(dto.getUser()));
		}
		
		if(dto.getLine() != null) {
			slot.setLine(lineConverter.fromDto(dto.getLine()));
		}
		
		return slot;
	}

	@Override
	public SlotDto fromEntity(Slot entity) {
		SlotDto slot = new SlotDto();
		
		slot.setSlotId(entity.getSlotId());
		slot.setActiveFg(entity.getActiveFg());
		slot.setStartDate(entity.getStartDate());
		slot.setEndDate(entity.getEndDate());
		
		if(entity.getUser() != null) {
			slot.setUser(userConverter.fromEntity(entity.getUser()));
		}
		
		if(entity.getLine() != null) {
			slot.setLine(lineConverter.fromEntity(entity.getLine()));
		}
		
		return slot;
	}

}
