package com.line.store.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.line.store.constant.ApiState;
import com.line.store.dao.ScheduleDao;
import com.line.store.dao.StoreDao;
import com.line.store.dto.ApiResponse;
import com.line.store.dto.ScheduleDto;
import com.line.store.dto.converter.ScheduleConverter;
import com.line.store.entity.Schedule;
import com.line.store.entity.Store;
import com.line.store.exception.ApiException;
import com.line.store.util.Utils;

@Service
public class ScheduleService {

	@Autowired
	ScheduleDao scheduleDao;
	@Autowired
	StoreDao storeDao;

	@Autowired
	ScheduleConverter scheduleConverter;

	@Autowired
	Utils UTILS;

	public ApiResponse findByStoreId(String storeId) throws ApiException {
		Store store = storeDao.findById(storeId)
				.orElseThrow(() -> new ApiException(ApiState.SCHEDULE_NOT_FOUND.getCode(),
						ApiState.SCHEDULE_NOT_FOUND.getMessage()));

		List<ScheduleDto> schedules = scheduleConverter.fromEntity(scheduleDao.findByStore(store).stream()
				.filter(x -> x.getActiveFg().equals("S")).collect(Collectors.toList()));

		return ApiResponse.of(ApiState.SUCCESS.getCode(), ApiState.SUCCESS.getMessage(), schedules, schedules.size());
	}

	public ApiResponse findById(String scheduleId) throws ApiException {
		ScheduleDto schedule = scheduleConverter.fromEntity(scheduleDao.findById(scheduleId)
				.orElseThrow(() -> new ApiException(ApiState.SCHEDULE_NOT_FOUND.getCode(),
						ApiState.SCHEDULE_NOT_FOUND.getMessage())));

		return ApiResponse.of(ApiState.SUCCESS.getCode(), ApiState.SUCCESS.getMessage(), schedule, null);
	}

	public ApiResponse findByStoreDate(String storeId, Long dateLong) throws ApiException {
		Store store = storeDao.findById(storeId)
				.orElseThrow(() -> new ApiException(ApiState.SCHEDULE_NOT_FOUND.getCode(),
						ApiState.SCHEDULE_NOT_FOUND.getMessage()));

		LocalDate date = UTILS.longToLocalDate(dateLong);

		List<ScheduleDto> schedules = scheduleConverter.fromEntity(scheduleDao.findByStoreDate(store, date).stream()
				.filter(x -> x.getActiveFg().equals("S")).collect(Collectors.toList()));

		return ApiResponse.of(ApiState.SUCCESS.getCode(), ApiState.SUCCESS.getMessage(), schedules, schedules.size());
	}

	public ApiResponse save(String request) throws ApiException {
		Schedule schedule;
		List<ScheduleDto> schedules;

		JsonNode root;

		String scheduleId = null;
		String storeId = null;
		String name = null;
		String activeFg = null;
		LocalDate startDate = null;
		LocalDate endDate = null;
		String start = null;
		String end = null;
		String multipleFg = null;
		String capacityFg = null;
		Integer capacity = null;
		Integer reservedNu = null;
		String maxPerClientFg = null;
		Integer maxPerClient = null;

		try {
			root = new ObjectMapper().readTree(request);

			storeId = root.path("storeId").asText();
			name = root.path("name").asText();
			activeFg = root.path("activeFg").asText();
			startDate = UTILS.longToLocalDate(root.path("startDate").asLong());
			endDate = UTILS.longToLocalDate(root.path("endDate").asLong());
			start = root.path("start").asText();
			end = root.path("end").asText();
			multipleFg = root.findPath("multipleFg").asText();
			capacityFg = root.findPath("capacityFg").asText();
			capacity = root.findPath("capacity").asInt();
			reservedNu = root.findPath("reservedNu").asInt();
			maxPerClientFg = root.findPath("maxPerClientFg").asText();
			maxPerClient = root.findPath("maxPerClient").asInt();

		} catch (JsonProcessingException e) {
			throw new ApiException(ApiState.NO_APPLICATION_PROCESSED.getCode(),
					ApiState.NO_APPLICATION_PROCESSED.getMessage(), e.getMessage());
		}

		Store store = storeDao.findById(storeId).orElseThrow(
				() -> new ApiException(ApiState.STORE_NOT_FOUND.getCode(), ApiState.STORE_NOT_FOUND.getMessage()));

		Period period = Period.between(startDate, endDate);
		LocalDate date = startDate;

		schedules = new ArrayList<>();

		while (period.getDays() >= 0) {

			schedule = new Schedule();
			scheduleId = UUID.randomUUID().toString();

			try {

				schedule.setName(name);
				schedule.setScheduleId(scheduleId);
				schedule.setStore(store);
				schedule.setActiveFg(activeFg);
				schedule.setDate(date);
				schedule.setStart(start);
				schedule.setEnd(end);
				schedule.setMultipleFg(multipleFg);
				schedule.setCapacityFg(capacityFg);
				schedule.setCapacity(capacityFg.equals("N") ? null : capacity);
				schedule.setReservedNu(reservedNu);
				schedule.setMaxPerClientFg(maxPerClientFg);
				schedule.setMaxPerClient(maxPerClientFg.equals("N") ? null : maxPerClient);

				schedules.add(scheduleConverter.fromEntity(scheduleDao.save(schedule)));

			} catch (Exception e) {
				throw new ApiException(ApiState.NO_APPLICATION_PROCESSED.getCode(),
						ApiState.NO_APPLICATION_PROCESSED.getMessage(), e.getMessage());
			}

			date = date.plusDays(1);
			period = Period.between(date, endDate);
		}

		return ApiResponse.of(ApiState.SUCCESS.getCode(), ApiState.SUCCESS.getMessage(), schedules, schedules.size());
	}

	public ApiResponse update(String request) throws ApiException {
		Schedule schedule;
		ScheduleDto scheduleDto;

		JsonNode root;

		String scheduleId = null;
		String name = null;
		String storeId = null;
		String activeFg = null;
		LocalDate date = null;
		String start = null;
		String end = null;
		String multipleFg = null;
		String capacityFg = null;
		Integer capacity = null;
		Integer reservedNu = null;
		String maxPerClientFg = null;
		Integer maxPerClient = null;

		try {
			root = new ObjectMapper().readTree(request);

			scheduleId = root.path("scheduleId").asText();
			name = root.path("name").asText();
			storeId = root.path("storeId").asText();
			activeFg = root.path("activeFg").asText();
			date = UTILS.longToLocalDate(root.path("date").asLong());
			start = root.path("start").asText();
			end = root.path("end").asText();
			multipleFg = root.findPath("multipleFg").asText();
			capacityFg = root.findPath("capacityFg").asText();
			capacity = root.findPath("capacity").asInt();
			reservedNu = root.findPath("reservedNu").asInt();
			maxPerClientFg = root.findPath("maxPerClientFg").asText();
			maxPerClient = root.findPath("maxPerClient").asInt();

		} catch (JsonProcessingException e) {
			throw new ApiException(ApiState.NO_APPLICATION_PROCESSED.getCode(),
					ApiState.NO_APPLICATION_PROCESSED.getMessage(), e.getMessage());
		}

		Store store = storeDao.findById(storeId).orElseThrow(
				() -> new ApiException(ApiState.STORE_NOT_FOUND.getCode(), ApiState.STORE_NOT_FOUND.getMessage()));

		schedule = scheduleDao.findById(scheduleId)
				.orElseThrow(() -> new ApiException(ApiState.SCHEDULE_NOT_FOUND.getCode(),
						ApiState.SCHEDULE_NOT_FOUND.getMessage()));

		try {

			schedule.setName(name);
			schedule.setScheduleId(scheduleId);
			schedule.setStore(store);
			schedule.setActiveFg(activeFg);
			schedule.setDate(date);
			schedule.setStart(start);
			schedule.setEnd(end);
			schedule.setMultipleFg(multipleFg);
			schedule.setCapacityFg(capacityFg);
			schedule.setCapacity(capacityFg.equals("N") ? null : capacity);
			schedule.setReservedNu(capacityFg.equals("N") ? null : reservedNu);
			schedule.setMaxPerClientFg(maxPerClientFg);
			schedule.setMaxPerClient(maxPerClientFg.equals("N") ? null : maxPerClient);

			scheduleDto = scheduleConverter.fromEntity(scheduleDao.save(schedule));

		} catch (Exception e) {
			throw new ApiException(ApiState.NO_APPLICATION_PROCESSED.getCode(),
					ApiState.NO_APPLICATION_PROCESSED.getMessage(), e.getMessage());
		}

		return ApiResponse.of(ApiState.SUCCESS.getCode(), ApiState.SUCCESS.getMessage(), scheduleDto, null);
	}

}
