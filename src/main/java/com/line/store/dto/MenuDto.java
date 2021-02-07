package com.line.store.dto;

import java.util.List;

public class MenuDto {

	private Integer menuId;
	private String name;
	private Integer orderNu;
	private String icon;
	private List<SubmenuDto> submenus;

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
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

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public List<SubmenuDto> getSubmenus() {
		return submenus;
	}

	public void setSubmenus(List<SubmenuDto> submenus) {
		this.submenus = submenus;
	}

	@Override
	public String toString() {
		return "MenuDto [menuId=" + menuId + ", name=" + name + ", orderNu=" + orderNu + ", icon=" + icon + "]";
	}
}
