package com.line.store.dto.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.line.store.dto.ScheduleDto;
import com.line.store.entity.Schedule;
import com.line.store.util.Utils;

@Component
public class ScheduleConverter extends AbstractConverter<Schedule, ScheduleDto> {

	@Autowired
	StoreConverter storeConverter;

	@Autowired
	Utils UTILS;

	@Override
	public Schedule fromDto(ScheduleDto dto) {
		Schedule schedule = new Schedule();

		schedule.setScheduleId(dto.getScheduleId());
		schedule.setName(dto.getName());
		schedule.setDate(UTILS.longToLocalDate(dto.getDate()));
		schedule.setStart(dto.getStart());
		schedule.setEnd(dto.getEnd());
		schedule.setActiveFg(dto.getActiveFg());
		schedule.setMultipleFg(dto.getMultipleFg());
		schedule.setCapacityFg(dto.getCapacityFg());
		schedule.setCapacity(dto.getCapacity());
		schedule.setReservedNu(dto.getReservedNu());
		schedule.setMaxPerClientFg(dto.getMaxPerClientFg());
		schedule.setMaxPerClient(dto.getMaxPerClient());

		if (dto.getStore() != null) {
			schedule.setStore(storeConverter.fromDto(dto.getStore()));
		}

		return schedule;
	}

	@Override
	public ScheduleDto fromEntity(Schedule entity) {
		ScheduleDto schedule = new ScheduleDto();

		schedule.setScheduleId(entity.getScheduleId());
		schedule.setName(entity.getName());
		schedule.setDate(UTILS.LocalDateToLong(entity.getDate()));
		schedule.setStart(entity.getStart());
		schedule.setEnd(entity.getEnd());
		schedule.setActiveFg(entity.getActiveFg());
		schedule.setMultipleFg(entity.getMultipleFg());
		schedule.setCapacityFg(entity.getCapacityFg());
		schedule.setCapacity(entity.getCapacity());
		schedule.setReservedNu(entity.getReservedNu());
		schedule.setMaxPerClientFg(entity.getMaxPerClientFg());
		schedule.setMaxPerClient(entity.getMaxPerClient());

		if (entity.getStore() != null) {
			schedule.setStore(storeConverter.fromEntity(entity.getStore()));
		}

		return schedule;
	}

}
