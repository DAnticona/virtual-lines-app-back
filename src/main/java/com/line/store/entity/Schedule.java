package com.line.store.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "schedule")
public class Schedule implements Serializable {

	private static final long serialVersionUID = -1000896721424649491L;

	@Id
	@Column(name = "schedule_id")
	private String scheduleId;
	@ManyToOne
	@JoinColumn(name = "store_id", referencedColumnName = "store_id")
	private Store store;
	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "active_fg", nullable = false)
	private String activeFg;
	@Column(name = "date", nullable = false)
	private LocalDate date;
	@Column(name = "start", nullable = false)
	private String start;
	@Column(name = "end", nullable = false)
	private String end;
	@Column(name = "multiple_fg", nullable = false)
	private String multipleFg;
	@Column(name = "capacity_fg", nullable = false)
	private String capacityFg;
	@Column(name = "capacity", nullable = true)
	private Integer capacity;
	@Column(name = "reserved_nu", nullable = true)
	private Integer reservedNu;
	@Column(name = "max_per_client_fg", nullable = false)
	private String maxPerClientFg;
	@Column(name = "max_per_client", nullable = true)
	private Integer maxPerClient;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(String scheduleId) {
		this.scheduleId = scheduleId;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public String getActiveFg() {
		return activeFg;
	}

	public void setActiveFg(String activeFg) {
		this.activeFg = activeFg;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
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
		return "Schedule [scheduleId=" + scheduleId + ", store=" + store + ", name=" + name + ", activeFg=" + activeFg
				+ ", date=" + date + ", start=" + start + ", end=" + end + ", multipleFg=" + multipleFg
				+ ", capacityFg=" + capacityFg + ", capacity=" + capacity + ", reservedNu=" + reservedNu
				+ ", maxPerClientFg=" + maxPerClientFg + ", maxPerClient=" + maxPerClient + "]";
	}
}
