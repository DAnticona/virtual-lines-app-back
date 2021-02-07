package com.line.store.dto;

public class CategoryDto {
	
	private Integer categoryId;
	private String name;
	private String activeFg;
	
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
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
		return "CategoryDto [categoryId=" + categoryId + ", name=" + name + ", activeFg=" + activeFg + "]";
	}
}
