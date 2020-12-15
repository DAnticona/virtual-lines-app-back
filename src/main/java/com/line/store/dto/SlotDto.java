package com.line.store.dto;

public class SlotDto {
	
	private String slotId;
	private LineDto line;
	private UserDto user;
	private String activeFg;
	private String attendedFg;
	private Long startDate;
	private Long endDate;
	
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
	public Long getStartDate() {
		return startDate;
	}
	public void setStartDate(Long startDate) {
		this.startDate = startDate;
	}
	public Long getEndDate() {
		return endDate;
	}
	public void setEndDate(Long endDate) {
		this.endDate = endDate;
	}
	public String getAttendedFg() {
		return attendedFg;
	}
	public void setAttendedFg(String attendedFg) {
		this.attendedFg = attendedFg;
	}
	@Override
	public String toString() {
		return "SlotDto [slotId=" + slotId + ", line=" + line + ", user=" + user + ", activeFg=" + activeFg
				+ ", attendedFg=" + attendedFg + ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}
}
