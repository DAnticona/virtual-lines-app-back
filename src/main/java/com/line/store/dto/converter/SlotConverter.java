package com.line.store.dto.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.line.store.dto.SlotDto;
import com.line.store.entity.Slot;
import com.line.store.util.Utils;

@Component
public class SlotConverter extends AbstractConverter<Slot, SlotDto> {

	@Autowired
	LineConverter lineConverter;
	@Autowired
	UserConverter userConverter;
	@Autowired
	StoreConverter storeConverter;
	@Autowired
	RoleConverter roleConverter;

	@Autowired
	Utils UTILS;

	@Override
	public Slot fromDto(SlotDto dto) {
		Slot slot = new Slot();

		slot.setSlotId(dto.getSlotId());
		slot.setActiveFg(dto.getActiveFg());
		slot.setAttendedFg(dto.getAttendedFg());
		slot.setStartDate(UTILS.longToLocalDateTime(dto.getStartDate()));
		slot.setEndDate(UTILS.longToLocalDateTime(dto.getEndDate()));

		if (dto.getLine() != null) {
			slot.setLine(lineConverter.fromDto(dto.getLine()));
		}

		return slot;
	}

	@Override
	public SlotDto fromEntity(Slot entity) {
		SlotDto slot = new SlotDto();

		slot.setSlotId(entity.getSlotId());
		slot.setActiveFg(entity.getActiveFg());
		slot.setAttendedFg(entity.getAttendedFg());
		slot.setStartDate(UTILS.LocalDateTimeTolong(entity.getStartDate()));
		slot.setEndDate(UTILS.LocalDateTimeTolong(entity.getEndDate()));

		if (entity.getLine() != null) {
			slot.setLine(lineConverter.fromEntity(entity.getLine()));
		}

		if (entity.getUser() != null) {
			slot.setUser(userConverter.fromEntity(entity.getUser()));
		}

		return slot;
	}

}
