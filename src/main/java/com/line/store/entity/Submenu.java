package com.line.store.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "submenu")
public class Submenu implements Serializable {

	private static final long serialVersionUID = 1103707929990173678L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "submenu_id")
	private Integer submenuId;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "order_nu", nullable = false)
	private Integer orderNu;
	
	@Column(name = "path", nullable = false)
	private String path;
	
	@MapsId("idMenu")
	@ManyToOne
	@JoinColumn(name = "menu_id", referencedColumnName = "menu_id")
	@JsonBackReference
	private Menu menu;

	public Integer getSubmenuId() {
		return submenuId;
	}

	public void setSubmenuId(Integer submenuId) {
		this.submenuId = submenuId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getOrderNu() {
		return orderNu;
	}

	public void setOrderNu(Integer orderNu) {
		this.orderNu = orderNu;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	@Override
	public String toString() {
		return "Submenu [submenuId=" + submenuId + ", name=" + name + ", orderNu=" + orderNu + ", path=" + path
				+ ", menu=" + menu + "]";
	}
}
