package com.line.store.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "line")
public class Line implements Serializable {
	
	private static final long serialVersionUID = 1057640603804939813L;

	@Id
	@Column(name = "line_id")
	private String lineId;
	
	@ManyToOne
	@JoinColumn(name = "store_id", referencedColumnName = "store_id")
	private Store store;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "active_fg", nullable = false)
	private String activeFg;

	public String getLineId() {
		return lineId;
	}

	public void setLineId(String lineId) {
		this.lineId = lineId;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
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

	@Override
	public String toString() {
		return "Line [lineId=" + lineId + ", store=" + store + ", name=" + name + ", activeFg=" + activeFg + "]";
	}

}
