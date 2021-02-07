package com.line.store.dto;

public class ScheduleDto {

	private String scheduleId;
	private StoreDto store;
	private String name;
	private String activeFg;
	private Long date;
	private String start;
	private String end;
	private String multipleFg;
	private String capacityFg;
	private Integer capacity;
	private Integer reservedNu;
	private String maxPerClientFg;
	private Integer maxPerClient;

	public String getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(String scheduleId) {
		this.scheduleId = scheduleId;
	}

	public StoreDto getStore() {
		return store;
	}

	public void setStore(StoreDto store) {
		this.store = store;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getActiveFg() {
		return activeFg;
	}

	public void setActiveFg(String activeFg) {
		this.activeFg = activeFg;
	}

	public Long getDate() {
		return date;
	}

	public void setDate(Long date) {
		this.date = date;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public String getCapacityFg() {
		return capacityFg;
	}

	public void setCapacityFg(String capacityFg) {
		this.capacityFg = capacityFg;
	}

	public String getMultipleFg() {
		return multipleFg;
	}

	public void setMultipleFg(String multipleFg) {
		this.multipleFg = multipleFg;
	}

	public Integer getReservedNu() {
		return reservedNu;
	}

	public void setReservedNu(Integer reservedNu) {
		this.reservedNu = reservedNu;
	}

	public String getMaxPerClientFg() {
		return maxPerClientFg;
	}

	public void setMaxPerClientFg(String maxPerClientFg) {
		this.maxPerClientFg = maxPerClientFg;
	}

	public Integer getMaxPerClient() {
		return maxPerClient;
	}

	public void setMaxPerClient(Integer maxPerClient) {
		this.maxPerClient = maxPerClient;
	}

	@Override
	public String toString() {
		return "ScheduleDto [scheduleId=" + scheduleId + ", store=" + store + ", name=" + name + ", activeFg="
				+ activeFg + ", date=" + date + ", start=" + start + ", end=" + end + ", multipleFg=" + multipleFg
				+ ", capacityFg=" + capacityFg + ", capacity=" + capacity + ", reservedNu=" + reservedNu
				+ ", maxPerClientFg=" + maxPerClientFg + ", maxPerClient=" + maxPerClient + "]";
	}
}
