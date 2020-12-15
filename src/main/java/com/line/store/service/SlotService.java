package com.line.store.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.line.store.constant.ApiState;
import com.line.store.dao.LineDao;
import com.line.store.dao.SlotDao;
import com.line.store.dao.UserDao;
import com.line.store.dto.ApiResponse;
import com.line.store.dto.SlotDto;
import com.line.store.dto.converter.SlotConverter;
import com.line.store.entity.Line;
import com.line.store.entity.Slot;
import com.line.store.entity.User;
import com.line.store.exception.ApiException;
import com.line.store.util.Utils;

@Service
public class SlotService {

	@Autowired
	SlotDao slotDao;
	@Autowired
	UserDao userDao;
	@Autowired
	LineDao lineDao;

	@Autowired
	SlotConverter slotConverter;

	@Autowired
	Utils UTILS;

	public ApiResponse findActivesByLineId(String lineId) throws ApiException {

		Line line = lineDao.findById(lineId).orElseThrow(
				() -> new ApiException(ApiState.LINE_NOT_FOUND.getCode(), ApiState.LINE_NOT_FOUND.getMessage()));

		List<SlotDto> slots = slotConverter.fromEntity(slotDao.findByLine(line).stream()
				.filter(x -> x.getActiveFg().equals("S")).collect(Collectors.toList()));

		return ApiResponse.of(ApiState.SUCCESS.getCode(), ApiState.SUCCESS.getMessage(), slots, slots.size());
	}
	
	public ApiResponse findActiveByLineIdUserId(String lineId, String userId) throws ApiException {

		Line line = lineDao.findById(lineId).orElseThrow(
				() -> new ApiException(ApiState.LINE_NOT_FOUND.getCode(), ApiState.LINE_NOT_FOUND.getMessage()));
		
		User user = userDao.findById(userId).orElseThrow(
				() -> new ApiException(ApiState.USER_NOT_FOUND.getCode(), ApiState.USER_NOT_FOUND.getMessage()));

		SlotDto slotDto;
		Slot slot = slotDao.findByLineUserActiveFg(line, user, "S").orElse(null);
		
		if(slot != null) {
			slotDto = slotConverter.fromEntity(slot);
		} else {
			slotDto = null;
		}

		return ApiResponse.of(ApiState.SUCCESS.getCode(), ApiState.SUCCESS.getMessage(), slotDto, null);
	}

	public ApiResponse save(String request) throws ApiException {
		Slot slot;
		SlotDto slotUserDto;

		JsonNode root;

		String slotId = null;
		String userId = null;
		String lineId = null;
		String activeFg = null;
		String attendedFg = null;
		LocalDateTime startDate = null;
		LocalDateTime endDate = null;

		try {
			root = new ObjectMapper().readTree(request);

			slotId = root.path("slotId").asText();
			userId = root.path("userId").asText();
			lineId = root.path("lineId").asText();
			activeFg = root.path("activeFg").asText();
			attendedFg = root.path("attendedFg").asText();
			startDate = UTILS.longToLocalDateTime(root.path("startDate").asLong());
			endDate = UTILS.longToLocalDateTime(root.path("endDate").asLong());

		} catch (JsonProcessingException e) {
			throw new ApiException(ApiState.NO_APPLICATION_PROCESSED.getCode(),
					ApiState.NO_APPLICATION_PROCESSED.getMessage(), e.getMessage());
		}

		User user = userDao.findById(userId).orElseThrow(
				() -> new ApiException(ApiState.USER_NOT_FOUND.getCode(), ApiState.USER_NOT_FOUND.getMessage()));

		Line line = lineDao.findById(lineId).orElseThrow(
				() -> new ApiException(ApiState.LINE_NOT_FOUND.getCode(), ApiState.LINE_NOT_FOUND.getMessage()));

		if (StringUtils.isBlank(slotId)) {
			slot = new Slot();
			slotId = UUID.randomUUID().toString();
		} else {
			slot = slotDao.findById(slotId).orElseThrow(
					() -> new ApiException(ApiState.SLOT_NOT_FOUND.getCode(), ApiState.SLOT_NOT_FOUND.getMessage()));
		}

		try {

			slot.setSlotId(slotId);
			slot.setUser(user);
			slot.setLine(line);
			slot.setActiveFg(activeFg);
			slot.setAttendedFg(attendedFg);
			slot.setStartDate(startDate);
			slot.setEndDate(endDate);

			slotUserDto = slotConverter.fromEntity(slotDao.save(slot));

		} catch (Exception e) {
			throw new ApiException(ApiState.NO_APPLICATION_PROCESSED.getCode(),
					ApiState.NO_APPLICATION_PROCESSED.getMessage(), e.getMessage());
		}

		return ApiResponse.of(ApiState.SUCCESS.getCode(), ApiState.SUCCESS.getMessage(), slotUserDto);
	}

}
