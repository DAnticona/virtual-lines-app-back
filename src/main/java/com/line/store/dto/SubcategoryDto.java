package com.line.store.dto;

public class SubcategoryDto {
	
	private String subcategoryId;
	private CategoryDto category;
	private String name;
	private String activeFg;
	
	public CategoryDto getCategory() {
		return category;
	}
	public void setCategory(CategoryDto category) {
		this.category = category;
	}
	public String getSubcategoryId() {
		return subcategoryId;
	}
	public void setSubcategoryId(String subcategoryId) {
		this.subcategoryId = subcategoryId;
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
		return "SubcategoryDto [categoryId=" + category + ", subcategoryId=" + subcategoryId + ", name=" + name
				+ ", activeFg=" + activeFg + "]";
	}
}
