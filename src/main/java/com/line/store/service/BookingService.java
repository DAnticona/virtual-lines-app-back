package com.line.store.service;

import java.time.LocalDate;
import java.util.ArrayList;
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
import com.line.store.dao.BookingDao;
import com.line.store.dao.ScheduleDao;
import com.line.store.dao.UserDao;
import com.line.store.dto.ApiResponse;
import com.line.store.dto.BookingDto;
import com.line.store.dto.converter.BookingConverter;
import com.line.store.entity.Booking;
import com.line.store.entity.Schedule;
import com.line.store.entity.User;
import com.line.store.exception.ApiException;
import com.line.store.util.Utils;

@Service
public class BookingService {

	@Autowired
	BookingDao bookingDao;
	@Autowired
	ScheduleDao scheduleDao;
	@Autowired
	UserDao userDao;

	@Autowired
	BookingConverter bookingConverter;

	@Autowired
	Utils UTILS;

	public ApiResponse findActivesByScheduleId(String scheduleId) throws ApiException {

		Schedule schedule = scheduleDao.findById(scheduleId)
				.orElseThrow(() -> new ApiException(ApiState.SCHEDULE_NOT_FOUND.getCode(),
						ApiState.SCHEDULE_NOT_FOUND.getMessage()));

		List<BookingDto> bookings = bookingConverter.fromEntity(bookingDao.findBySchedule(schedule).stream()
				.filter(x -> x.getActiveFg().equals("S")).collect(Collectors.toList()));

		return ApiResponse.of(ApiState.SUCCESS.getCode(), ApiState.SUCCESS.getMessage(), bookings, bookings.size());
	}

	public ApiResponse findActiveByScheduleIdUserId(String scheduleId, String userId) throws ApiException {

		Schedule schedule = scheduleDao.findById(scheduleId)
				.orElseThrow(() -> new ApiException(ApiState.SCHEDULE_NOT_FOUND.getCode(),
						ApiState.SCHEDULE_NOT_FOUND.getMessage()));

		User user = userDao.findById(userId).orElseThrow(
				() -> new ApiException(ApiState.USER_NOT_FOUND.getCode(), ApiState.USER_NOT_FOUND.getMessage()));

		BookingDto bookingDto;
		Booking booking = bookingDao.findByScheduleUserActiveFg(schedule, user, "S").orElse(null);

		if (booking != null) {
			bookingDto = bookingConverter.fromEntity(booking);
		} else {
			bookingDto = null;
		}

		return ApiResponse.of(ApiState.SUCCESS.getCode(), ApiState.SUCCESS.getMessage(), bookingDto, null);
	}

	public ApiResponse save(String request) throws ApiException {
		Booking booking;
		BookingDto bookingDto;

		JsonNode root;

		String bookingId = null;
		String userId = null;
		Integer quantity = null;
		String scheduleId = null;
		String activeFg = null;
		String attendedFg = null;

		try {
			root = new ObjectMapper().readTree(request);

			bookingId = root.path("bookingId").asText();
			userId = root.path("userId").asText();
			quantity = root.path("quantity").asInt();
			scheduleId = root.path("scheduleId").asText();
			activeFg = root.path("activeFg").asText();
			attendedFg = root.path("attendedFg").asText();

		} catch (JsonProcessingException e) {
			throw new ApiException(ApiState.NO_APPLICATION_PROCESSED.getCode(),
					ApiState.NO_APPLICATION_PROCESSED.getMessage(), e.getMessage());
		}

		User user = userDao.findById(userId).orElseThrow(
				() -> new ApiException(ApiState.USER_NOT_FOUND.getCode(), ApiState.USER_NOT_FOUND.getMessage()));

		if (user.getRole().getRoleId() != 3) {
			throw new ApiException(ApiState.USER_NOT_CLIENT.getCode(), ApiState.USER_NOT_CLIENT.getMessage());
		}

		Schedule schedule = scheduleDao.findById(scheduleId)
				.orElseThrow(() -> new ApiException(ApiState.SCHEDULE_NOT_FOUND.getCode(),
						ApiState.SCHEDULE_NOT_FOUND.getMessage()));

		if (StringUtils.isBlank(bookingId)
				&& bookingDao.findByScheduleUserActiveFg(schedule, user, "S").orElse(null) != null) {
			throw new ApiException(ApiState.USER_ALREADY_ASSIGNED.getCode(),
					ApiState.USER_ALREADY_ASSIGNED.getMessage());
		}

		if (StringUtils.isBlank(bookingId)) {
			booking = new Booking();
			bookingId = UUID.randomUUID().toString();
		} else {
			booking = bookingDao.findById(bookingId)
					.orElseThrow(() -> new ApiException(ApiState.BOOKING_NOT_FOUND.getCode(),
							ApiState.BOOKING_NOT_FOUND.getMessage()));
		}

		try {

			booking.setBookingId(bookingId);
			booking.setUser(user);
			booking.setQuantity(quantity);
			booking.setSchedule(schedule);
			booking.setActiveFg(activeFg);
			booking.setAttendedFg(attendedFg);

			if (schedule.getMultipleFg().equals("S") && schedule.getMaxPerClientFg().equals("S")
					&& quantity > schedule.getMaxPerClient()) {
				throw new ApiException(ApiState.QUOTA_INCORRECT.getCode(), ApiState.QUOTA_INCORRECT.getMessage());
			}

			if (schedule.getCapacityFg().equals("S")
					&& quantity > (schedule.getCapacity() - schedule.getReservedNu())) {
				throw new ApiException(ApiState.QUOTA_INCORRECT.getCode(), ApiState.QUOTA_INCORRECT.getMessage());
			}
			schedule.setReservedNu(schedule.getReservedNu() + quantity);
			scheduleDao.save(schedule);

			bookingDto = bookingConverter.fromEntity(bookingDao.save(booking));

		} catch (Exception e) {
			throw new ApiException(ApiState.NO_APPLICATION_PROCESSED.getCode(),
					ApiState.NO_APPLICATION_PROCESSED.getMessage(), e.getMessage());
		}

		return ApiResponse.of(ApiState.SUCCESS.getCode(), ApiState.SUCCESS.getMessage(), bookingDto);
	}

	public List<Booking> getBookingsToPush() {

		List<Booking> bookings = new ArrayList<>();
		
		LocalDate today = LocalDate.now();

		List<Schedule> schedules = scheduleDao.findByDate(today);

		for (Schedule schedule : schedules) {
			bookings.addAll(bookingDao.findBySchedule(schedule));
		}

		return bookings;

	}
}
