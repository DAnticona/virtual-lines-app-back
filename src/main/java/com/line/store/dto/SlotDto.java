package com.line.store.dto;

import java.time.LocalTime;

public class SlotDto {
	
	private String slotId;
	private LineDto line;
	private UserDto user;
	private String activeFg;
	private LocalTime startDate;
	private LocalTime endDate;
	
	public String getSlotId() {
		return slotId;
	}
	public void setSlotId(String slotId) {
		this.slotId = slotId;
	}
	public LineDto getLine() {
		return line;
	}
	public void setLine(LineDto line) {
		this.line = line;
	}
	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
		this.user = user;
	}
	public String getActiveFg() {
		return activeFg;
	}
	public void setActiveFg(String activeFg) {
		this.activeFg = activeFg;
	}
	public LocalTime getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalTime startDate) {
		this.startDate = startDate;
	}
	public LocalTime getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalTime endDate) {
		this.endDate = endDate;
	}
	
	@Override
	public String toString() {
		return "SlotDto [slotId=" + slotId + ", line=" + line + ", user=" + user + ", activeFg=" + activeFg
				+ ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}
}
