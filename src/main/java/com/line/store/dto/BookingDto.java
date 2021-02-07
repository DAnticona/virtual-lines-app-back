package com.line.store.dto;

public class BookingDto {

	private String bookingId;
	private UserDto user;
	private Integer quantity;
	private ScheduleDto schedule;
	private String activeFg;
	private String attendedFg;

	public String getBookingId() {
		return bookingId;
	}

	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public ScheduleDto getSchedule() {
		return schedule;
	}

	public void setSchedule(ScheduleDto schedule) {
		this.schedule = schedule;
	}

	public String getActiveFg() {
		return activeFg;
	}

	public void setActiveFg(String activeFg) {
		this.activeFg = activeFg;
	}

	public String getAttendedFg() {
		return attendedFg;
	}

	public void setAttendedFg(String attendedFg) {
		this.attendedFg = attendedFg;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "BookingDto [bookingId=" + bookingId + ", user=" + user + ", quantity=" + quantity + ", schedule="
				+ schedule + ", activeFg=" + activeFg + ", attendedFg=" + attendedFg + "]";
	}
}
