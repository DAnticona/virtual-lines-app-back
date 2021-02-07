package com.line.store.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "booking")
public class Booking implements Serializable {

	private static final long serialVersionUID = 3197524094114682878L;

	@Id
	@Column(name = "booking_id")
	private String bookingId;
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	private User user;
	@Column(name = "quantity", nullable = false)
	private Integer quantity;
	@ManyToOne
	@JoinColumn(name = "schedule_id", referencedColumnName = "schedule_id")
	private Schedule schedule;
	@Column(name = "active_fg", nullable = false)
	private String activeFg;
	@Column(name = "attended_fg", nullable = false)
	private String attendedFg;

	public String getBookingId() {
		return bookingId;
	}

	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
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
		return "Booking [bookingId=" + bookingId + ", user=" + user + ", quantity=" + quantity + ", schedule="
				+ schedule + ", activeFg=" + activeFg + ", attendedFg=" + attendedFg + "]";
	}
}
