package com.line.store.dto.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.line.store.dto.BookingDto;
import com.line.store.entity.Booking;

@Component
public class BookingConverter extends AbstractConverter<Booking, BookingDto> {

	@Autowired
	ScheduleConverter scheduleConverter;
	@Autowired
	UserConverter userConverter;

	@Override
	public Booking fromDto(BookingDto dto) {
		Booking booking = new Booking();

		booking.setBookingId(dto.getBookingId());
		booking.setQuantity(dto.getQuantity());
		booking.setActiveFg(dto.getActiveFg());
		booking.setAttendedFg(dto.getAttendedFg());

		if (dto.getSchedule() != null) {
			booking.setSchedule(scheduleConverter.fromDto(dto.getSchedule()));
		}
		return booking;
	}

	@Override
	public BookingDto fromEntity(Booking entity) {
		BookingDto booking = new BookingDto();

		booking.setBookingId(entity.getBookingId());
		booking.setQuantity(entity.getQuantity());
		booking.setActiveFg(entity.getActiveFg());
		booking.setAttendedFg(entity.getAttendedFg());

		if (entity.getSchedule() != null) {
			booking.setSchedule(scheduleConverter.fromEntity(entity.getSchedule()));
		}

		if (entity.getUser() != null) {
			booking.setUser(userConverter.fromEntity(entity.getUser()));
		}

		return booking;
	}

}
