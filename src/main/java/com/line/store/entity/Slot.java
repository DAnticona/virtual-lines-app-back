package com.line.store.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "slot")
public class Slot implements Serializable {
	
	private static final long serialVersionUID = -7474204343190163055L;

	@Id
	@Column(name = "slot_id")
	private String slotId;
	
	@ManyToOne
	@JoinColumn(name = "line_id", referencedColumnName = "line_id")
	@JsonBackReference
	private Line line;
	
	@ManyToOne(optional = true)
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	@JsonBackReference
	private User user;
	
	@Column(name = "active_fg", nullable = false)
	private String activeFg;
	
	@Column(name = "attended_fg", nullable = false)
	private String attendedFg;
	
	@Column(name = "start_date", nullable = false)
	private LocalDateTime startDate;
	
	@Column(name = "end_date")
	private LocalDateTime endDate;

	public String getSlotId() {
		return slotId;
	}

	public void setSlotId(String slotId) {
		this.slotId = slotId;
	}

	public Line getLine() {
		return line;
	}

	public void setLine(Line line) {
		this.line = line;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getActiveFg() {
		return activeFg;
	}

	public void setActiveFg(String activeFg) {
		this.activeFg = activeFg;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
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
		return "Slot [slotId=" + slotId + ", line=" + line + ", user=" + user + ", activeFg=" + activeFg
				+ ", attendedFg=" + attendedFg + ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}
}
