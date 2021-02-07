package com.line.store.dto;

public class SubmenuDto {

	private Integer submenuId;
	private String name;
	private Integer orderNu;
	private String path;
	private Integer menuId;

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

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	@Override
	public String toString() {
		return "SubmenuDto [submenuId=" + submenuId + ", name=" + name + ", orderNu=" + orderNu + ", path=" + path
				+ ", menuId=" + menuId + "]";
	}

}
